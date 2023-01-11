package com.ronal.blog.controller;

import com.ronal.blog.dto.ErrorDTO;
import com.ronal.blog.exceptions.RequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHamdler(RequestException ex){
        ErrorDTO errorDTO = ErrorDTO.builder().code(ex.getCode()).errorDescription(ex.getMessage()).build();
        return new ResponseEntity<>(errorDTO,ex.getStatus());
    }

}
