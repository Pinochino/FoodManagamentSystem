package com.example.Microservice.dto.request;

import com.example.Microservice.model.Category;
import com.example.Microservice.model.Customer;
import com.example.Microservice.model.FoodImage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodRequest {

    UUID foodId;

    String foodName;

    String image;

    String description;

    BigDecimal price;

    String brand;
    List<FoodImage> images;

    Customer customer;

    Category category;
}
