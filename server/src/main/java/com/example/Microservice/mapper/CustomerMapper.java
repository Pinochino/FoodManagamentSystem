package com.example.Microservice.mapper;

import com.example.Microservice.dto.CustomerRequest;
import com.example.Microservice.model.Customer;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomer(CustomerRequest customerRequest);

    void updateCustomerFromRequest(CustomerRequest customerRequest, @MappingTarget Customer customer);
}
