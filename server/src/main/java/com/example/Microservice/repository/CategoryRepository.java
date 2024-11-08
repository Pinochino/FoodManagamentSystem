package com.example.Microservice.repository;

import com.example.Microservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "category-repository")
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findCategoryByName(String categoryName);

    boolean existsCategoriesByName(String name);
}
