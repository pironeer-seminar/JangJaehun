package com.example.pironeer.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // @Valid 예외 처리
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> response = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            response.put("message", fieldError.getDefaultMessage());
            break; // 첫 번째 오류 메시지만 반환
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
