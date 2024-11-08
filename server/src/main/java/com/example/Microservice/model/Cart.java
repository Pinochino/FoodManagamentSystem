package com.example.Microservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

    @Id
    UUID cartId;

    String status;

    Date date;

    Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    Customer customer;

}
