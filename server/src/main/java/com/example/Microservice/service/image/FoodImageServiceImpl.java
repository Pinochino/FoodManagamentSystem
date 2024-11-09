package com.example.Microservice.service.image;

import com.example.Microservice.dto.request.FoodImageRequest;
import com.example.Microservice.exceptions.image.FoodImageNotFoundException;
import com.example.Microservice.model.Food;
import com.example.Microservice.model.FoodImage;
import com.example.Microservice.repository.FoodImageRepository;
import com.example.Microservice.service.food.FoodService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FoodImageServiceImpl implements FoodImageService{

    FoodImageRepository foodImageRepository;
    FoodService foodService;

    @Autowired
    public FoodImageServiceImpl(FoodImageRepository foodImageRepository, FoodService foodService) {
        this.foodImageRepository = foodImageRepository;
        this.foodService = foodService;
    }

    @Override
    public List<FoodImage> getAllFoodImages() {
        return null;
    }

    @Override
    public FoodImage getFoodImageById(UUID id) {
        return foodImageRepository.findById(id).orElseThrow(() -> new FoodImageNotFoundException("Can't not found food by id " + id));
    }

    @Override
    public List<FoodImageRequest> saveImage(List<MultipartFile> files, UUID foodId) throws Exception {
        Food food = foodService.getFoodById(foodId);
        List<FoodImageRequest> savedImageDto = new ArrayList<>();
        for (MultipartFile file : files){
            try{
                FoodImage foodImage = new FoodImage();
                foodImage.setImageName(file.getOriginalFilename());
                foodImage.setImageType(file.getContentType());
                foodImage.setImage(new SerialBlob(file.getBytes()));
                foodImage.setFood(food);

                FoodImage savedImage = foodImageRepository.save(foodImage);
                String downloadUrl = "/api/v1/images/image/download/" + savedImage.getImageId();
                savedImage.setDownloadUrl(downloadUrl);
                foodImageRepository.save(savedImage);

                FoodImageRequest foodImageRequest = new FoodImageRequest();
                foodImageRequest.setImageId(savedImage.getImageId());
                foodImageRequest.setImageName(savedImage.getImageName());
                foodImageRequest.setDownloadUrl(downloadUrl);

                savedImageDto.add(foodImageRequest);

            } catch (IOException  | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public void updateFoodImage(UUID id, MultipartFile file) {
        FoodImage image = getFoodImageById(id);
        try {
            image.setImageName(file.getOriginalFilename());
            image.setImageType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            foodImageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void deleteFoodImageById(UUID id) {
        foodImageRepository.findById(id).ifPresentOrElse(foodImageRepository::delete, () -> {
            throw new FoodImageNotFoundException("No image found with id: " + id);
        } );
    }
}
