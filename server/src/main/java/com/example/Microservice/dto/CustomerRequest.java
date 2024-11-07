package com.example.Microservice.dto;

import com.example.Microservice.model.Product;
import com.example.Microservice.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.Date;
import java.util.List;
import java.util.UUID;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    @Getter
    @Setter
    UUID customerId;

    @Getter
    @Setter
    String username;

    @Getter
    @Setter
    String email;

    String password;

    String avatar;

    String avatarUrl;

    @Getter
    @Setter
    Date created_at;

    @Getter
    @Setter
    Date updated_at;

    @Getter
    @Setter
    Boolean isDeleted;

    @Getter
    List<Role> roleList;

    @Getter
    List<Product> productList;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
