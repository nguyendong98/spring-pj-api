package com.springboot.training.services;

import com.springboot.training.controllers.request.ProductCreateRequest;
import com.springboot.training.dto.mapper.ProductMapper;
import com.springboot.training.dto.mapper.UserMapper;
import com.springboot.training.dto.model.ProductDto;
import com.springboot.training.exception.BRSException;
import com.springboot.training.exception.EntityType;
import com.springboot.training.exception.ExceptionType;
import com.springboot.training.models.Product;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
import com.springboot.training.repo.ProductRepository;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.springboot.training.exception.EntityType.PRODUCT;
import static com.springboot.training.exception.EntityType.USER;
import static com.springboot.training.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.springboot.training.exception.ExceptionType.ENTITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Stream<ProductDto> getAllProduct() {

        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::toProductDto);
    }

    @Override
    public ProductDto createProduct(ProductCreateRequest productCreateRequest) {
        Product product = productRepository.findByName(productCreateRequest.getName());
        if (product == null) {
            product = new Product()
                    .setName(productCreateRequest.getName())
                    .setDescription(productCreateRequest.getDescription())
                    .setPrice(productCreateRequest.getPrice());
            log.info("product is {}", product);
            return ProductMapper.toProductDto(productRepository.save(product));
        }
        throw exception(PRODUCT, DUPLICATE_ENTITY, productCreateRequest.getName());
    }

    @Override
    public String deleteById(Long id) {
        productRepository.deleteById(id);
        return "Remove product from entity successfully";
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }


}
