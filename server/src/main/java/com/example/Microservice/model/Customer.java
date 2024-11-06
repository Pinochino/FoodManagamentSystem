package com.example.Microservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID customerId;


    @Column(name = "username")
    @Getter
    @Setter
    String username;

    @Getter
    @Setter
    @Email(message = "Customer's email is not valid")
    @Column(name = "email")
    String email;


    @NotNull(message = "Customer's password is required")
    @Column(name = "password")
    String password;

    @Getter
    @Column(name = "avatar")
    String avatar;

    @Getter
    @Setter
    @CreatedDate
    @Column(name = "created_at")
    Date created_at;

    @Getter
    @Setter
    @LastModifiedDate
    @Column(name = "updated_at")
    Date updated_at;

    @Getter
    @Setter
    @Column(name = "is_deleted")
    Boolean isDeleted;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    List<Role> roleList;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    List<Product> productList;


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
