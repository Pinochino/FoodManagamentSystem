package com.example.Microservice.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Food")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID foodId;

    @Column(name = "foodName")
    String foodName;

    @Column(name = "description")
    String description;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "brand")
    String brand;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FoodImage> images;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category category;

    public Food(String foodName, String description, BigDecimal price, String brand, Category category) {
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
    }
}
