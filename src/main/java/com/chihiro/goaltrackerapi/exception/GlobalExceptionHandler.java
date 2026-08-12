package com.chihiro.goaltrackerapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(GoalNotFoundException.class)
    public ResponseEntity<String> handleGoalNotFound(GoalNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
