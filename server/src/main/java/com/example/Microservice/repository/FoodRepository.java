package com.example.Microservice.repository;

import com.example.Microservice.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(path = "product-repository")
public interface FoodRepository extends JpaRepository<Food, UUID> {

    Food findFoodByFoodName(String productName);

    List<Food> findFoodByCategory(String category);

    List<Food> findFoodByBrand(String brand);

    List<Food> findFoodByBrandAndCategory(String brand, String category);

    Long countFoodByBrandAndFoodName(String brand, String foodName);
}
