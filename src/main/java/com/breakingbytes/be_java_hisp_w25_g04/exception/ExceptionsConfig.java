package com.breakingbytes.be_java_hisp_w25_g04.exception;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsConfig {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> yetFound(NotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), 404);
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
