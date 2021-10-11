package com.springboot.training.repo;

import com.springboot.training.models.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

//    @Autowired
//    private ProductRepository underTest;
//
//    @Test
//    void itShouldCheckIfProductExist() {
//        String name = "laptop";
//        Product product = new Product(
//                null,
//                "laptop",
//                "This is laptop",
//                3000
//        );
//        underTest.save(product);
//        Boolean expected = underTest.selectExistProduct(name);
//        assertThat(expected).isTrue();
//    }
}