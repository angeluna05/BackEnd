package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Celulas;
import com.example.ytalentbackend.Auth.AuthResponse;
import com.sun.jdi.request.InvalidRequestStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex) {
        logger.warn("ResourceNotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidRequestStateException.class)
    public ResponseEntity<String> handleInvalidRequestException(InvalidRequestStateException ex) {
        logger.warn("InvalidRequestException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private String extractRelevantMessage(String message) {
        if (message == null) {
            return "No se proporcionó mensaje de error.";
        }

        if (message.contains("Duplicate entry")) {
            int start = message.indexOf("Duplicate entry");
            int end = message.indexOf("for key");
            if (start != -1 && end != -1) {
                return message.substring(start, end + "for key".length());
            }
        }

        if (message.contains("ConstraintViolationImpl")) {
            int start = message.indexOf("interpolatedMessage='");
            int end = message.indexOf("', propertyPath=");
            if (start != -1 && end != -1) {
                return message.substring(start + "interpolatedMessage='".length(), end);
            }
        }

        return message;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Formato del JSON incorrecto: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        AuthResponse errorResponse = new AuthResponse();
        errorResponse.setMessage("Errores de validación");
        errorResponse.setErrors(errors); // Agrega este campo en AuthResponse
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Celulas.FieldValidationException.class)
    public ResponseEntity<Map<String, String>> handleFieldValidationException(Celulas.FieldValidationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put(ex.getFieldName(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<AuthResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    logger.error("DataIntegrityViolationException: ", ex);
    String message = extractRelevantMessage(ex.getMessage());

    // Personalización para "correo ya existe"
    if (message.contains("Duplicate entry") && message.contains("correo")) {
        message = "El correo ya existe. Por favor, usa otro correo.";
    }

    AuthResponse errorResponse = AuthResponse.builder()
            .message("Error de integridad de datos")
            .errors(Map.of("correo", message)) // Campo personalizado con el mensaje
            .build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
}

    // Manejo de SQLIntegrityConstraintViolationException
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<AuthResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        logger.error("SQLIntegrityConstraintViolationException: ", ex);
        String message = extractRelevantMessage(ex.getMessage());
        AuthResponse errorResponse = AuthResponse.builder()
                .message("Violación de restricción de integridad de base de datos")
                .errors(Map.of("sql_error", message))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // Manejo genérico de SQLException
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<AuthResponse> handleSQLException(SQLException ex) {
        logger.error("SQLException: ", ex);
        AuthResponse errorResponse = AuthResponse.builder()
                .message("Error en la base de datos")
                .errors(Map.of("sql_error", ex.getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // Manejo de BadSqlGrammarException (errores de sintaxis SQL)
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<AuthResponse> handleBadSqlGrammarException(BadSqlGrammarException ex) {
        logger.error("BadSqlGrammarException: ", ex);
        AuthResponse errorResponse = AuthResponse.builder()
                .message("Error de sintaxis en la consulta SQL")
                .errors(Map.of("sql_error", ex.getMostSpecificCause().getMessage()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
