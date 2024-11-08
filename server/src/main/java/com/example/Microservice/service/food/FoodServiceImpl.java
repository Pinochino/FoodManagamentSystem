package com.example.Microservice.service.food;

import com.example.Microservice.dto.request.FoodRequest;
import com.example.Microservice.model.Food;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodServiceImpl implements FoodService {
    @Override
    public List<Food> getAllFoods() {
        return null;
    }

    @Override
    public Optional<Food> getFoodById(UUID id) throws Exception {
        return Optional.empty();
    }

    @Override
    public Food createFood(FoodRequest foodRequest) {
        return null;
    }

    @Override
    public Food updateFood(UUID id, FoodRequest foodRequest) throws Exception {
        return null;
    }

    @Override
    public void deleteFoodById(UUID id) {

    }

    @Override
    public List<Food> getFoodsByCategory(String category) {
        return null;
    }

    @Override
    public List<Food> getFoodsByName(String foodName) {
        return null;
    }

    @Override
    public List<Food> getFoodsByBrand(String brand) {
        return null;
    }

    @Override
    public List<Food> getFoodsByBrandAndCategory(String brand, String category) {
        return null;
    }

    @Override
    public List<Food> getFoodsByNameAndCategory(String foodName, String category) {
        return null;
    }

    @Override
    public Long countFoodsByBrandAndName(String brand, String name) {
        return null;
    }
}