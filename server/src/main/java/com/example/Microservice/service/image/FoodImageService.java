package com.example.Microservice.service.image;

import com.example.Microservice.dto.request.FoodImageRequest;
import com.example.Microservice.model.FoodImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public interface FoodImageService {

    List<FoodImage> getAllFoodImages();

    FoodImage getFoodImageById(UUID id);

    List<FoodImageRequest> saveImage(List<MultipartFile> file, UUID foodId) throws Exception;

    void updateFoodImage(UUID id, MultipartFile file);

    void deleteFoodImageById(UUID id);

}
