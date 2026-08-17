package com.chihiro.goaltrackerapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chihiro.goaltrackerapi.dto.response.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(GoalNotFoundException.class)
    public ResponseEntity<String> handleGoalNotFound(GoalNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
        MethodArgumentNotValidException e
    )  {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult()
            .getFieldErrors()
            .forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });

        ValidationErrorResponse response = 
            new ValidationErrorResponse(
                "入力に誤りがあります",
                errors
            );

        return ResponseEntity.badRequest().body(response);
    }
}
