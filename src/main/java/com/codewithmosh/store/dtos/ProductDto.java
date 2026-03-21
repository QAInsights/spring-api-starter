package com.codewithmosh.store.dtos;

import com.codewithmosh.store.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDto {

    @Setter
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;


}
