package com.springboot.training.services;

import com.springboot.training.dto.mapper.ProductMapper;
import com.springboot.training.dto.model.ProductDto;
import com.springboot.training.models.Product;
import com.springboot.training.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl underTest;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProductServiceImpl(productRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProduct() {
        List<Product> mockProducts = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            mockProducts.add(new Product(
                    "test"+i,
                    "this is table",
                    30000
            ));
        }

        // 2. define behavior of Repository
        when(productRepository.findAll()).thenReturn(mockProducts);

        // 3. call service method
        Stream<ProductDto> actualBooks = underTest.getAllProduct();

        // 4. assert the result
        assertThat(actualBooks.count()).isEqualTo(mockProducts.size());

        // 4.1 ensure repository is called
        verify(productRepository).findAll();
    }

    @Test
    void canCreateProduct() {

        Product product = new Product(
                "photograph",
                "this is photograph",
                30000
        );
//        when
        underTest.createProduct(product);

        //then
        ArgumentCaptor<Product> productArgumentCaptor =
                ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());
        ProductDto capturedProduct = ProductMapper.toProductDto(productArgumentCaptor.getValue());
        assertNotNull(capturedProduct.getName());
    }

    @Test
    void canDeleteById() {

        // given
        long id = 1321321321;
//        given(productRepository.existsById(id))
//                .willReturn(true);
        // when
        underTest.deleteById(id);

        // then
        verify(productRepository).deleteById(id);
    }
}