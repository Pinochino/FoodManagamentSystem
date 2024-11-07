package com.example.Microservice.service.file;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {


    public String uploadFile(String path, MultipartFile file) throws IOException;

    InputStream getResourceFile(String path, String fileName) throws FileNotFoundException;

}
