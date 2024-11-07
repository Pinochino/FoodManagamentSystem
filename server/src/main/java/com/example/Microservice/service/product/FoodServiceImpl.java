package com.example.Microservice.service.product;

import com.example.Microservice.dto.ProductRequest;

import com.example.Microservice.model.Food;
import com.example.Microservice.repository.FoodRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FoodServiceImpl implements FoodService {
    FoodRepository foodRepository;



    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getAllProducts() {
        return foodRepository.findAll();
    }

    @Override
    public Optional<Food> getProductById(UUID id) throws Exception {
        Optional<Food> product = foodRepository.findById(id);
        if (product.isEmpty()){
                throw new Exception("Product have been not existed");
        }
        return product;
    }

    @Override
    public Food createProduct(ProductRequest productRequest) {

        return null;
    }

    @Override
    public Food updateProduct(UUID id, ProductRequest productRequest) {

        return null;
    }

    @Override
    public void deleteProduct(UUID id) {
        foodRepository.deleteById(id);
    }
}
