package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductController(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @GetMapping()
    public List<ProductDto> getAllProducts(
            @RequestParam(required = false, name = "categoryId"
    ) Byte categoryId) {

        List<Product> products;

        if(categoryId != null) {
            products = productRepository.findAllByCategoryId(categoryId);
//            return productRepository.findAllByCategoryId(categoryId).stream()
//                    .map((p) -> new ProductDto(p.getId(), p.getName(),
//                            p.getDescription(), p.getPrice(), p.getCategory().getId()))
//                    .toList();
        }
        else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream().map(productMapper::toDto).toList();

//        return productRepository.findAll().stream()
//                .map((p) -> new ProductDto(p.getId(), p.getName(),
//                        p.getDescription(), p.getPrice(), p.getCategory().getId()))
//                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);

        if (product != null)
            return ResponseEntity.ok(productMapper.toDto(product));
        else
            return ResponseEntity.notFound().build();

    }
}
