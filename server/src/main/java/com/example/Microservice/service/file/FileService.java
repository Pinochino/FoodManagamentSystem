package com.example.Microservice.service.file;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public interface FileService {


    public String saveFile(MultipartFile fileToSave) throws IOException;



}
