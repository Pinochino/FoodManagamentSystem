package com.example.Microservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID customerId;


    @Column(name = "username")
    String username;


    @Email()
    @Column(name = "email")
    String email;



    @Column(name = "password")
    String password;

    @Column(name = "avatar")
    String avatar;

    String avatarUrl;

    @CreatedDate
    @Column(name = "created_at")
    Date created_at;


    @LastModifiedDate
    @Column(name = "updated_at")
    Date updated_at;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    // Method to set creation date only once, at the time of creation
    @PrePersist
    protected void onCreate() {
        created_at = new Date(); // Set created_at only once
    }

    // Method to update the modification date each time the entity is updated
    @PreUpdate
    protected void onUpdate() {
        updated_at = new Date(); // Update updated_at with the current date/time on each update
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    List<Role> roleList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartId")
    List<Cart> carts;

}
