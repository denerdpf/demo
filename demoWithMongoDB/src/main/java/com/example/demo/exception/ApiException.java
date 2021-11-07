package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime timesTamp;

    public ApiException(String message, HttpStatus status, ZonedDateTime timesTamp) {
        this.message = message;
        this.status = status;
        this.timesTamp = timesTamp;
    }

}