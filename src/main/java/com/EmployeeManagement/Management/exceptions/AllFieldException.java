package com.EmployeeManagement.Management.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllFieldException {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        System.out.println("EXCEPTION: " + ex.getClass().getName());

        return ResponseEntity
                .badRequest()
                .body("All values are required");
    }
}
