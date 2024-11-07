package com.example.Microservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID employeeId;

    @Column(name = "employeeName")
    String employeeName;

    String name;

    String address;

    @OneToMany(mappedBy = "employeeId",cascade = CascadeType.ALL)
    List<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    List<Food> foods;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    List<Order> orders;
}
