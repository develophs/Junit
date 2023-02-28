package com.study.junitproject.web.handler;

import com.study.junitproject.web.dto.response.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> apiException(MethodArgumentNotValidException e){
        return new ResponseEntity<>(new CMRespDto(-1, e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
}
