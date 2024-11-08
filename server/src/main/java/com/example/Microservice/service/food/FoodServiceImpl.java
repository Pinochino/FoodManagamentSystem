package com.example.Microservice.service.food;

import com.example.Microservice.dto.request.FoodRequest;
import com.example.Microservice.exceptions.food.FoodNotFoundException;
import com.example.Microservice.model.Category;
import com.example.Microservice.model.Food;

import com.example.Microservice.repository.CategoryRepository;
import com.example.Microservice.repository.FoodRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodServiceImpl implements FoodService {

    FoodRepository foodRepository;

    CategoryRepository categoryRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository, CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Optional<Food> getFoodById(UUID id) {
        return Optional.ofNullable(foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException("Can't not find food by id: " + id)));
    }

    @Override
    public Food addFood(FoodRequest foodRequest) {
        // check if the category is found in the DB
        // If yes, set it as th new product category
        // If no, the save it as a new category
        // The set as the new product category

        Category category = Optional.ofNullable(categoryRepository.findCategoryByName(foodRequest.getCategory().getName())).orElseGet(() -> {
            Category newCategory = new Category(foodRequest.getCategory().getName());
            return categoryRepository.save(newCategory);
        });
        foodRequest.setCategory(category);
        return foodRepository.save(createFood(foodRequest, category));
    }

    private Food createFood(FoodRequest foodRequest, Category category) {
        return Food.builder().foodName(foodRequest.getFoodName()).description(foodRequest.getDescription()).brand(foodRequest.getBrand()).price(foodRequest.getPrice()).category(category).build();
    }

    @Override
    public Food updateFood(UUID id, FoodRequest foodRequest) {
        return foodRepository.findById(id)
                .map(existingFood -> updateExistingFood(existingFood, foodRequest))
                .map(foodRepository::save)
                .orElseThrow(() -> new FoodNotFoundException("Food not found !"));

    }

    private Food updateExistingFood(Food existingFood, FoodRequest foodRequest) {
        existingFood.setFoodName(foodRequest.getFoodName());
        existingFood.setDescription(foodRequest.getDescription());
        existingFood.setBrand(foodRequest.getBrand());
        existingFood.setPrice(foodRequest.getPrice());

        Category category = categoryRepository.findCategoryByName(foodRequest.getCategory().getName());
        existingFood.setCategory(category);

        return existingFood;
    }

    @Override
    public void deleteFoodById(UUID id) {
        foodRepository.findById(id).ifPresentOrElse(foodRepository::delete, () -> {
            throw new FoodNotFoundException("Can't not find food by id: " + id);
        });
    }

    @Override
    public List<Food> getFoodsByCategory(String category) {
        return foodRepository.findFoodByCategory(category);
    }

    @Override
    public Food getFoodByName(String foodName) {
        return foodRepository.findFoodByFoodName(foodName);
    }

    @Override
    public List<Food> getFoodsByBrand(String brand) {
        return foodRepository.findFoodByBrand(brand);
    }

    @Override
    public List<Food> getFoodsByBrandAndCategory(String brand, String category) {
        return foodRepository.findFoodByBrandAndCategory(brand, category);
    }


    @Override
    public Long countFoodsByBrandAndName(String brand, String name) {
        return foodRepository.countFoodByBrandAndFoodName(brand, name);
    }
}