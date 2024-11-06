package com.example.Microservice.service.product;

import com.example.Microservice.dto.ProductRequest;
import com.example.Microservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ProductService {

        List<Product> getAllProducts();

        Optional<Product> getProductById(UUID id) throws Exception;
        Product createProduct(ProductRequest productRequest);

        Product updateProduct(UUID id, ProductRequest productRequest) throws Exception;

        void deleteProduct(UUID id);
}
