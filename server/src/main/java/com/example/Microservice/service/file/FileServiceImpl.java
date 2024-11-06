package com.example.Microservice.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService{

    String STORAGE_DIRECTORY = "D:\\Microservice\\Microservice\\src\\main\\java\\com\\example\\Microservice\\uploads";
    @Override
    public String saveFile(MultipartFile fileToSave) throws IOException {
        File directory = new File(STORAGE_DIRECTORY);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create directory: " + STORAGE_DIRECTORY);
            }
        }

        if (fileToSave == null || fileToSave.isEmpty()) {
            throw new IllegalArgumentException("No file provided");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + fileToSave.getOriginalFilename());
        if(!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)){
            throw new SecurityException("Can't save file to " + targetFile.getParent());
        }
        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return targetFile.getAbsolutePath();
    }



}
