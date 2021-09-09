package com.springboot.training.configurations;

import com.springboot.training.models.Product;
import com.springboot.training.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(
//            ProductRepository repository) {
//        return args -> {
//            Product table = new Product(
//                    "table",
//                    "This is a table",
//                    300000
//            );
//            Product laptop = new Product(
//                    "laptop",
//                    "This is a laptop",
//                    500000
//            );
//
//            repository.saveAll(
//                    List.of(table, laptop)
//            );
//        };
//    }

}
