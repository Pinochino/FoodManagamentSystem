package com.example.Microservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"order\"")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID orderId;

    Date date;

    Double amount;

    String status;

    @OneToOne
    @JoinColumn(name = "delivery_id")  // This adds a foreign key reference to Delivery table
    Delivery delivery;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderItemId")
    List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    Payment payment;

}
