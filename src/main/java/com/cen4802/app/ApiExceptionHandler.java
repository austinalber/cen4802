package com.cen4802.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(
            IllegalArgumentException exception, HttpServletRequest request) {
        log.warn("Request rejected: method={}, path={}, reason={}",
                request.getMethod(), request.getRequestURI(), exception.getMessage(), exception);

        Map<String, String> response = Map.of(
                "error", "Bad Request",
                "message", exception.getMessage(),
                "path", request.getRequestURI(),
                "timestamp", Instant.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
