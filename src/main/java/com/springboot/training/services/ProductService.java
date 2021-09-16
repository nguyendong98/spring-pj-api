package com.springboot.training.services;

import com.springboot.training.dto.model.ProductDto;
import com.springboot.training.dto.model.UserDto;
import com.springboot.training.models.Product;
import com.springboot.training.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

public interface ProductService {
    List<ProductDto> getAllProduct();

}
