package com.springboot.training.dto.mapper;

import com.springboot.training.dto.model.ProductDto;

import com.springboot.training.models.Product;

import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public static ProductDto toProductDto(Product product) {
        return new ProductDto()
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice());
    }
}
