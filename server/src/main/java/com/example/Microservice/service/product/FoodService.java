package com.example.Microservice.service.product;

import com.example.Microservice.dto.ProductRequest;
import com.example.Microservice.model.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface FoodService {

        List<Food> getAllProducts();

        Optional<Food> getProductById(UUID id) throws Exception;
        Food createProduct(ProductRequest productRequest);

        Food updateProduct(UUID id, ProductRequest productRequest) throws Exception;

        void deleteProduct(UUID id);
}
