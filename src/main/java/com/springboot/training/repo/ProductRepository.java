package com.springboot.training.repo;

import com.springboot.training.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
