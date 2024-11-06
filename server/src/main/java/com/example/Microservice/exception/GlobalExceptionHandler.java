package com.example.Microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductException> handleProductNotFound(ProductNotFoundException exception){
        ProductException response = new ProductException(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CustomerException> handleCustomerNotFound(CustomerNotFoundException exception){
        CustomerException response = new CustomerException(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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



}
