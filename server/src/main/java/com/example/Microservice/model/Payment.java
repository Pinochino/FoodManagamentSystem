package com.example.Microservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID paymentId;

    @Column(name = "date")
    Date date;

    @Column(name = "amount")
    Double amount;

    @Column(name = "method")
    String method;

    @Column(name = "status")
    String status;

    @OneToOne(mappedBy = "orderId")
    Order order;
}
