package com.example.Microservice.exceptions.file;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(String s) {
        super(s);
    }
}
