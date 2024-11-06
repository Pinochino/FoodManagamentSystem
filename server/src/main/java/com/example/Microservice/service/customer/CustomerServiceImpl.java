package com.example.Microservice.service.customer;

import com.example.Microservice.dto.CustomerRequest;
import com.example.Microservice.mapper.CustomerMapper;
import com.example.Microservice.model.Customer;
import com.example.Microservice.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        String encodedPassword = passwordEncoder.encode(customerRequest.getPassword());
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(UUID id, CustomerRequest customerRequest) throws Exception {
        Customer existingCustomer = getCustomerById(id)
                .orElseThrow(() -> new Exception("Customer not found"));
        customerMapper.updateCustomerFromRequest(customerRequest, existingCustomer);
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    @Override
    public boolean login(String email, String password) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomerByEmail(email));
        return customer.filter(value -> passwordEncoder.matches(password, value.getPassword())).isPresent();
    }
}
