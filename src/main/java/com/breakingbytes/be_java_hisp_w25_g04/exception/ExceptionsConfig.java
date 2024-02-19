package com.breakingbytes.be_java_hisp_w25_g04.exception;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsConfig {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> notFoundException(NotFoundException e){
        return new ResponseEntity<>(
                new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND.value())
                , HttpStatus.NOT_FOUND);
    }
}
