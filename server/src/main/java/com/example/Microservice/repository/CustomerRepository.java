package com.example.Microservice.repository;

import com.example.Microservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "customer-repository")
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findCustomerByEmail(String email);
}
