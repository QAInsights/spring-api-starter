package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers(
            @RequestHeader(required = false, name="X-Auth-Token") String authHeader,
            @RequestParam(required = false, defaultValue = "", name="sort") String sortBy
    ) {
        System.out.println("Header " + authHeader);
        if (!Set.of("name", "email").contains(sortBy)) {
            sortBy = "name";
        }
        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);

        if(user == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(Optional.of(userMapper.toDto(user)));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest registerUserRequest,
            UriComponentsBuilder uriComponentsBuilder) {

        var user = userMapper.toEntity(registerUserRequest);
        var saveUser = userRepository.save(user);

        var userDto = userMapper.toDto(user);

        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();

        System.out.println(saveUser);
        return ResponseEntity.created(uri).body(userDto);
    }
}
