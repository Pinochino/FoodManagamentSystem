package com.example.Microservice.service.customer;

import com.example.Microservice.dto.CustomerRequest;
import com.example.Microservice.exception.CustomerNotFoundException;
import com.example.Microservice.model.Customer;
import com.example.Microservice.repository.CustomerRepository;
import com.example.Microservice.service.file.FileService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;


    PasswordEncoder passwordEncoder;

    FileService fileService;

    @Value("${project.upload}")
    String path;

    @Value("${project.url}")
    String baseUrl;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,PasswordEncoder passwordEncoder, FileService fileService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerRequest> getCustomerById(UUID id) {
//        1. Check the data in DB and if exist, fetch the data of given ID
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer have not existed " + id)));

//        2. generate avatarUrl
        String avatarUrl = baseUrl + "/file/"+ customer.get().getAvatar();

//        3. map to CustomerRequest object and return it
        return Optional.ofNullable(CustomerRequest.builder()
                .username(customer.get().getUsername())
                .email(customer.get().getEmail())
                .password(customer.get().getPassword())
                .avatar(customer.get().getAvatar())
                .isDeleted(customer.get().getIsDeleted())
                .created_at(customer.get().getCreated_at())
                .updated_at(customer.get().getUpdated_at())
                .avatarUrl(avatarUrl)
                .build());
    }

    @Override
    public CustomerRequest createCustomer(CustomerRequest customerRequest, MultipartFile file) throws IOException {
        // 1. Upload the file
        String uploadedFileName = fileService.uploadFile(path, file);

        // 2. Set the value of field "avatar" as filename
        customerRequest.setAvatar(uploadedFileName);

        String avatarUrl = baseUrl + "/file/" + uploadedFileName;
        customerRequest.setAvatarUrl(avatarUrl);

        // 3. Encode password
        String encodedPassword = passwordEncoder.encode(customerRequest.getPassword());

        // 4. Create and save the Customer entity
        Customer customer = Customer.builder()
                .username(customerRequest.getUsername())
                .email(customerRequest.getEmail())
                .password(encodedPassword) // Use the encoded password here
                .avatar(customerRequest.getAvatar())
                .isDeleted(customerRequest.getIsDeleted() != null ? customerRequest.getIsDeleted() : false)
                .created_at(customerRequest.getCreated_at() != null ? customerRequest.getCreated_at() : new Date())
                .updated_at(customerRequest.getUpdated_at() != null ? customerRequest.getUpdated_at() : new Date())
                .avatarUrl(avatarUrl) // Ensure avatarUrl is a field in Customer if needed
                .build();

        // 5. Save customer to the repository
        customerRepository.save(customer);

        // 6. Return the CustomerRequest for confirmation
        return customerRequest;
    }


    @Override
    public Customer updateCustomer(UUID id, CustomerRequest customerRequest) {

        return null;
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
