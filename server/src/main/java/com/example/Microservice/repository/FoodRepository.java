package com.example.Microservice.repository;

import com.example.Microservice.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "product-repository")
public interface FoodRepository extends JpaRepository<Food, UUID> {

    Food findProductByProductName(String productName);
}
