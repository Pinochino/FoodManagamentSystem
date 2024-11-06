package com.example.Microservice.repository;

import com.example.Microservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "product-repository")
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findProductByProductName(String productName);
}
