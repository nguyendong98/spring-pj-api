package com.springboot.training.services;

import com.springboot.training.controllers.request.ProductCreateRequest;
import com.springboot.training.dto.model.ProductDto;
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
     * @param productCreateRequest
     * @return
     */
    ProductDto createProduct(ProductCreateRequest productCreateRequest);

    /**
     * Delete product
     * @param id
     * @return
     */
    String deleteById(Long id);

}
