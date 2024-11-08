package com.example.Microservice.dto.request;

import com.example.Microservice.model.Food;
import com.example.Microservice.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @JsonProperty("customerId")
    UUID customerId;

    @JsonProperty("username")
    String username;

    @JsonProperty("email")
    String email;

    @JsonProperty("password")
    String password;

    @JsonProperty("avatar")
    String avatar;


    @JsonProperty("avatarUrl")
    String avatarUrl;

    @JsonProperty("created_at")
    Date created_at;

    @JsonProperty("updated_at")
    Date updated_at;

    @JsonProperty("isDeleted")
    Boolean isDeleted;

    List<Role> roleList;
    List<Food> foodList;


}
