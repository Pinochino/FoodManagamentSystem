package com.example.Microservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {

    UUID productId;

    String productName;

    String image;

    String description;

    BigDecimal price;
}
