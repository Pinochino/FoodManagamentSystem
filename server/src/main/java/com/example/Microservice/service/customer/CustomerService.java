package com.example.Microservice.service.customer;

import com.example.Microservice.dto.CustomerRequest;
import com.example.Microservice.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CustomerService {

    List<Customer> getAllCustomer();

    Optional<CustomerRequest> getCustomerById(UUID id);

    CustomerRequest createCustomer(CustomerRequest customerRequest, MultipartFile file) throws IOException;

    CustomerRequest updateCustomer(UUID id, CustomerRequest customerRequest, MultipartFile file) throws IOException;

    String deleteCustomer(UUID id) throws IOException;

    boolean login(String email, String password );
}
