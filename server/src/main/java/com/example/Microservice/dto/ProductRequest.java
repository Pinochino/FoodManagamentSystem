package com.example.Microservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    UUID productId;

    String productName;

    String image;

    String description;

    BigDecimal price;
}
