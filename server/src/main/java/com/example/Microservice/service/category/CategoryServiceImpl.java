package com.example.Microservice.service.category;

import com.example.Microservice.exceptions.AlreadyExistException;
import com.example.Microservice.exceptions.category.CategoryNotFoundException;
import com.example.Microservice.model.Category;
import com.example.Microservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category is not found with id " + id));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.ofNullable(category)
                .filter(c -> !categoryRepository.existsCategoriesByName(c.getName()))
                .map(categoryRepository :: save)
                .orElseThrow(() -> {
                    assert category != null;
                    return new AlreadyExistException(category.getName() + " already exist");
                });
    }

    @Override
    public Category updateCategory(Category category, UUID id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }


    @Override
    public void deleteCategoryById(UUID id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete, () -> {
                    throw new ResourceNotFoundException("Category not found");
                });
    }
}
