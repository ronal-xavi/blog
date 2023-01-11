package com.ronal.blog.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RequestException extends RuntimeException{
    private final String code;
    private final HttpStatus status;

    public RequestException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
