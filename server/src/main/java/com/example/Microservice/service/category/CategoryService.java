package com.example.Microservice.service.category;

import com.example.Microservice.model.Category;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {

    Category getCategoryById(UUID id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category addCategory(Category category);

    public Category updateCategory(Category category, UUID id);

    void deleteCategoryById(UUID id);

}
