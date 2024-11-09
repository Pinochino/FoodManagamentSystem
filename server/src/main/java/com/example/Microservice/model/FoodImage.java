package com.example.Microservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;
import java.util.UUID;

@Entity
@Table(name = "FoodImage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID imageId;


    String imageName;

    String imageType;

    @Lob
    Blob image;

    String downloadUrl;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foodId")
    Food food;
}
