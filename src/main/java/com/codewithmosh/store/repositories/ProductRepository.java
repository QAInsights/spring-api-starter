package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = "category")
    List<Product> findAllByCategoryId(Byte category_id);

    @EntityGraph(attributePaths = "category")
    @Query("SELECT p from Product p")
//    @Query("SELECT p from Product p JOIN FETCH p.category")
    List<Product> findAllWithCategory();
}