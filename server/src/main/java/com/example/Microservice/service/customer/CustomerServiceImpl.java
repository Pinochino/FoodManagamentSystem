package com.example.Microservice.service.customer;

import com.example.Microservice.dto.request.CustomerRequest;
import com.example.Microservice.exceptions.customer.CustomerNotFoundException;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileAlreadyExistsException("File already existed! Please enter another file name");
        }

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
    public CustomerRequest updateCustomer(UUID id, CustomerRequest customerRequest, MultipartFile file) throws IOException {
        // Tìm khách hàng theo ID
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        // Cập nhật avatar nếu có file mới
        String fileName = customer.getAvatar();
        if (file != null) {
            // Xóa file cũ nếu có
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            // Tải file mới lên
            fileName = fileService.uploadFile(path, file);
        }

        // Cập nhật các thông tin từ customerRequest vào đối tượng customer
        customer.setUsername(customerRequest.getUsername());
        customer.setEmail(customerRequest.getEmail());
        customer.setCreated_at(customerRequest.getCreated_at());
        customer.setUpdated_at(customerRequest.getUpdated_at());
        customer.setIsDeleted(customerRequest.getIsDeleted());
        customer.setPassword(customerRequest.getPassword());
        customer.setAvatar(fileName);

        // Lưu khách hàng đã cập nhật
        Customer updatedCustomer = customerRepository.save(customer);

        // Tạo đường dẫn URL cho avatar mới
        String avatarUrl = baseUrl + "/file/" + fileName;

        // Trả về CustomerRequest mới với thông tin đã cập nhật
        CustomerRequest updatedCustomerRequest = CustomerRequest.builder()
                .password(customerRequest.getPassword())
                .username(customerRequest.getUsername())
                .email(customerRequest.getEmail())
                .isDeleted(customerRequest.getIsDeleted())
                .updated_at(customerRequest.getUpdated_at())
                .created_at(customerRequest.getCreated_at())
                .avatarUrl(avatarUrl)
                .build();

        return updatedCustomerRequest;
    }



    @Override
    public String deleteCustomerById(UUID id) throws IOException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer"));
        id = customer.getCustomerId();

        Files.deleteIfExists(Paths.get(path + File.separator + customer.getAvatar()));
        customerRepository.deleteById(id);

        return "Customer delete with id: " + id;
    }

    @Override
    public boolean login(String email, String password) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomerByEmail(email));
        return customer.filter(value -> passwordEncoder.matches(password, value.getPassword())).isPresent();
    }
}
