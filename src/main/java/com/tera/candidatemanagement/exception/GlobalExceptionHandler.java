package com.tera.candidatemanagement.exception;

import com.tera.candidatemanagement.payload.ApiResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, List<String>>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            String message;
            if (err.contains(TypeMismatchException.class)) {
                Object rejected = err.getRejectedValue();
                message = String.format("Invalid value '%s' for field '%s'. Allowed values: MALE, FEMALE",
                        rejected != null ? rejected.toString() : "null",
                        err.getField());
            } else {
                message = err.getDefaultMessage();
            }
            errors.computeIfAbsent(err.getField(), key -> new ArrayList<>()).add(message);
        }
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(errors, "Validation failed"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(null, ex.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Map<String, List<String>>>> handleEmailConflict(EmailAlreadyExistsException ex) {
        Map<String, List<String>> error = Map.of("email", List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(error, "Conflict"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(null, ex.getMessage()));
    }
}