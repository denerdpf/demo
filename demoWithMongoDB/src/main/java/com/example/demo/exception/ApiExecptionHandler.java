package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExecptionHandler {

    @ExceptionHandler(value = {ApiRequestExecption.class})
    public ResponseEntity<Object> handleApiRequestExecption(ApiRequestExecption e){
        ApiException apiException = new ApiException (
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
