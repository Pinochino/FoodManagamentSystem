package com.example.Microservice.repository;

import com.example.Microservice.model.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "food-image-repository")
public interface FoodImageRepository extends JpaRepository<FoodImage, UUID> {
}
