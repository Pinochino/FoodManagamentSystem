package com.example.Microservice.service.food;

import com.example.Microservice.dto.request.FoodRequest;
import com.example.Microservice.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface FoodService {

        List<Food> getAllFoods();

        Optional<Food> getFoodById(UUID id) throws Exception;
        Food addFood(FoodRequest foodRequest);

        Food updateFood(UUID id, FoodRequest foodRequest) throws Exception;

        void deleteFoodById(UUID id);

        List<Food> getFoodsByCategory(String category);

        Food getFoodByName(String foodName);

        List<Food> getFoodsByBrand(String brand);

        List<Food> getFoodsByBrandAndCategory(String brand, String category);


        Long countFoodsByBrandAndName(String brand, String name);
}
