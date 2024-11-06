package com.example.Microservice.service.product;

import com.example.Microservice.dto.ProductRequest;
import com.example.Microservice.mapper.ProductMapper;
import com.example.Microservice.model.Product;
import com.example.Microservice.repository.ProductRepository;
import com.example.Microservice.service.product.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(UUID id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
                throw new Exception("Product have been not existed");
        }
        return product;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product =  productMapper.toProduct(productRequest);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(UUID id, ProductRequest productRequest) throws Exception {
        Product existingProduct = getProductById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        productMapper.updateProductFromRequest(productRequest, existingProduct);
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
