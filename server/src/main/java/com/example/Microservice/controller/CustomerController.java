package com.example.Microservice.controller;


import com.example.Microservice.dto.CustomerRequest;
import com.example.Microservice.exception.CustomerNotFoundException;
import com.example.Microservice.model.Customer;
import com.example.Microservice.service.customer.CustomerService;
import com.example.Microservice.service.file.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerController {

    CustomerService customerService;
    FileService fileService;

    @Autowired
    public CustomerController(CustomerService customerService, FileService fileService) {
        this.customerService = customerService;
        this.fileService = fileService;
    }


    @Operation
            (summary = "View a list of available customers")
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
    @GetMapping("/customers")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerRequest> getCustomerById(@PathVariable UUID id) {
        CustomerRequest customer = customerService.getCustomerById(id).orElseThrow(() -> new CustomerNotFoundException(("Not found the customer by id: " + id)));
        return ResponseEntity.ok(customer);
    }

    @Operation
            (summary = "Create a new customer")
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
    @PostMapping(value = "/customer/create", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<CustomerRequest> createCustomer(@RequestPart("file") MultipartFile file, @RequestPart("customerRequest") String customerRequest) throws IOException {
        CustomerRequest customerRequest1 = convertToCustomerRequest(customerRequest);
        return new ResponseEntity<>(customerService.createCustomer(customerRequest1, file), HttpStatus.CREATED);
    }

    private CustomerRequest convertToCustomerRequest(String customerRequestObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(customerRequestObj, CustomerRequest.class);
    }

    @Operation(summary = "Update customer by id")
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
    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerRequest> updateCustomer(@PathVariable UUID id, @RequestPart("customerRequest") String customerObj, @RequestPart MultipartFile file) throws Exception {
        if (file.isEmpty()) file = null;
        CustomerRequest customerRequest = convertToCustomerRequest(customerObj);
        CustomerRequest customer = customerService.updateCustomer(id, customerRequest, file);
        return ResponseEntity.ok(customer);
    }

    @Operation
            (summary = "Delete customer by id")
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
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID id) throws IOException {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }


}
