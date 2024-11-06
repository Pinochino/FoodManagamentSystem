package com.example.Microservice.mapper;

import com.example.Microservice.dto.ProductRequest;
import com.example.Microservice.model.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductRequest productRequest);

    void updateProductFromRequest(ProductRequest productRequest, @MappingTarget Product product);
}
