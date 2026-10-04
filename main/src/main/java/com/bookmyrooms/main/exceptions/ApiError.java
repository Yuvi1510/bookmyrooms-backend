package com.bookmyrooms.main.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
public class ApiError {
    private int statusCode;
    private String name;
    private String message;
    private String path;
    private Instant timestamp;

    public ApiError(HttpStatus status, String msg, String path){
        this.statusCode = status.value();
        this.name = status.name();
        this.message = msg;
        this.path = path;
        this.timestamp = Instant.now();
    }
}
