package com.ecommerce.app.account.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {
    UUID uuid = UUID.randomUUID();
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
        ErrorResponse errorResponse = new ErrorResponse( uuid.toString(),
                500,
                "VALIDATION_ERROR",
                "Database error: " + ex.getMessage()
        );
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request");
        ErrorResponse errorResponse = new ErrorResponse( uuid.toString(),
                400,
                "VALIDATION_ERROR",
                errorMessage
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
