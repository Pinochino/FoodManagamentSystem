package com.example.Microservice.controller;

import com.example.Microservice.dto.request.FoodRequest;
import com.example.Microservice.exceptions.food.FoodNotFoundException;
import com.example.Microservice.model.Food;
import com.example.Microservice.service.food.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.UUID;

@RestController
@Data
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin("http://localhost:4200")
public class FoodController {

    FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Operation
            (summary = "View a list of available products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description
                    = "Successfully retrieved list"),
            @ApiResponse(responseCode
                    = "401", description
                    = "You are not authorized to view the resource"),
            @ApiResponse(responseCode
                    = "403", description
                    = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode
                    = "404", description
                    = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/products")
    public List<Food> getAllFood() {
        return foodService.getAllFoods();
    }


    @GetMapping(path = "/product/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable UUID id) throws Exception {
        Food food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }

    @Operation
            (summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode
                    = "200", description
                    = "Successfully created customer"),
            @ApiResponse(responseCode
                    = "401", description
                    = "You are not authorized to view the resource"),
            @ApiResponse(responseCode
                    = "403", description
                    = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode
                    = "404", description
                    = "The resource you were trying to reach is not found")
    })
    @PostMapping("/product/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Food> createFood(@Valid  @RequestBody FoodRequest foodRequest) {
        Food food = foodService.addFood(foodRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(food);
    }

    @Operation(summary = "Update product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode
                    = "200", description
                    = "Successfully update customer"),
            @ApiResponse(responseCode
                    = "401", description
                    = "You are not authorized to view the resource"),
            @ApiResponse(responseCode
                    = "403", description
                    = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode
                    = "404", description
                    = "The resource you were trying to reach is not found")
    })
    @PutMapping("/product/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") UUID id, @Valid @RequestBody FoodRequest foodRequest) throws Exception {
        Food food = foodService.updateFood(id, foodRequest);
        return  ResponseEntity.ok(food);
    }

    @Operation
            (summary = "Delete product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode
                    = "200", description
                    = "Successfully delete customer"),
            @ApiResponse(responseCode
                    = "401", description
                    = "You are not authorized to view the resource"),
            @ApiResponse(responseCode
                    = "403", description
                    = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode
                    = "404", description
                    = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") UUID id){
        foodService.deleteFoodById(id);
        return ResponseEntity.noContent().build(); // Trả về 204 No Content
    }

}
