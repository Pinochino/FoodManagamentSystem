package com.example.Microservice.controller;

import com.example.Microservice.service.file.FileService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileController {

    FileService fileService;
    Logger log = Logger.getLogger(FileController.class.getName());

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload-file")
    public boolean uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.saveFile(file);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception during upload", e);
        }
        return true;
    }


}
