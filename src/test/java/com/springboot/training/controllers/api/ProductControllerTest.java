package com.springboot.training.controllers.api;

import com.springboot.training.dto.mapper.ProductMapper;
import com.springboot.training.dto.model.ProductDto;
import com.springboot.training.dto.response.Response;
import com.springboot.training.models.Product;
import com.springboot.training.repo.ProductRepository;
import com.springboot.training.services.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.springboot.training.dto.response.Response.Status.OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductServiceImpl productServiceImpl;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllProduct() {
        List<Product> mockProducts = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            mockProducts.add(new Product(
                    "test"+i,
                    "this is table",
                    30000
            ));
        }
        Stream<ProductDto> products = mockProducts.stream().map(ProductMapper::toProductDto);
        // 2. define behavior of Repository
        when(productServiceImpl.getAllProduct()).thenReturn(products);

        Response result = productController.getAllProduct();
        System.out.println(result);

        // then
        assertThat(result.getStatus()).isEqualTo(OK);
        assertThat(result.getPayload()).isEqualTo(products);
    }

    @Test
    void createProduct() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Product product = new Product(
                "test",
                "this is test",
                30000
        );
        when(productServiceImpl.createProduct(product)).thenReturn(product);
        Response result = productController.createProduct(product);
        assertThat(result.getStatus()).isEqualTo(OK);
        assertThat(result.getPayload()).isEqualTo(product);
    }

    @Test
    void deleteById() {
    }
}