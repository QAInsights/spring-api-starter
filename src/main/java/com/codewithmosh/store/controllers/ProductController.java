package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.dtos.UpdateProductRequest;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository,
                             ProductMapper productMapper,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
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

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
        @RequestBody ProductDto productDto, UriComponentsBuilder uriComponentsBuilder) {

        var categoryId = categoryRepository.findById(productDto.getCategoryId()).orElse(null);

        if (categoryId == null)
            return ResponseEntity.badRequest().build();

        var product = productMapper.toEntity(productDto);
        product.setCategory(categoryId);
        productRepository.save(product);

        productDto.setId(product.getId());

        return ResponseEntity.created(uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri()).body(productDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductRequest> updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest updateProductRequest
    ){
        var categoryId = categoryRepository.findById(updateProductRequest.getCategoryId()).orElse(null);
        if(categoryId == null)
            return ResponseEntity.badRequest().build();

        var product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        productMapper.update(updateProductRequest, product);
        product.setCategory(categoryId);
        productRepository.save(product);

        return ResponseEntity.ok(updateProductRequest);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        var product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
