package com.example.mission3.global.handler;

import com.example.mission3.global.dto.ResponseDto;
import com.example.mission3.global.handler.exception.CustomApiException;
import com.example.mission3.global.handler.exception.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomApiException.class)
    public ResponseDto<?> handleCustomApiException(CustomApiException e) {
        return ResponseDto.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomValidationException.class)
    public ResponseDto<?> handleValidationApiException(CustomValidationException e) {
        return ResponseDto.fail(e.getMessage(), e.getErrorMap());
    }
}
