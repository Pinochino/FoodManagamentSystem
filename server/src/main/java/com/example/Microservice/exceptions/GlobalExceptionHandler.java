package com.example.Microservice.exceptions;

import com.example.Microservice.exceptions.customer.CustomerNotFoundException;
import com.example.Microservice.exceptions.file.FileNotFoundException;
import com.example.Microservice.exceptions.food.FoodException;
import com.example.Microservice.exceptions.food.FoodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseEntity<FoodException> handleProductNotFound(FoodNotFoundException exception) {
        FoodException response = new FoodException(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleCustomerNotFound(CustomerNotFoundException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }


    //    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ProductException> handleException(Exception exception){
//        ProductException response = new ProductException(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "An unexpected exception: " + exception.getMessage(),
//                new Timestamp(System.currentTimeMillis())
//        );
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ProblemDetail handleFileNotFound(CustomerNotFoundException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }


}
