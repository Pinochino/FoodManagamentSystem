package com.example.Microservice.service.customer;

import com.example.Microservice.dto.CustomerRequest;
import com.example.Microservice.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CustomerService {

    List<Customer> getAllCustomer();

    Optional<Customer> getCustomerById(UUID id);

    Customer createCustomer(CustomerRequest customerRequest);

    Customer updateCustomer(UUID id, CustomerRequest customerRequest) throws Exception;

    void deleteCustomer(UUID id);

    boolean login(String email, String password );
}
