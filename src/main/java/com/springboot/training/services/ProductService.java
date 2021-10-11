package com.springboot.training.services;
import com.springboot.training.dto.model.ProductDto;
import com.springboot.training.models.Product;

import java.util.stream.Stream;

public interface ProductService {

    /**
     * Get all product
     *
     * @return
     */
    Stream<ProductDto> getAllProduct();

    /**
     * Create product
     * @param product
     * @return
     */
    Product createProduct(Product product);

    /**
     * Delete product
     * @param id
     * @return
     */
    String deleteById(Long id);

}
