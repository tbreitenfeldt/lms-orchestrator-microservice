package com.smoothstack.lms.orchestratorservice.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrchestratorExceptionController extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(OrchestratorExceptionController.class);

    @ExceptionHandler(value = CriticalDatabaseErrorException.class)
    public ResponseEntity<ApiError> exceptionHandler(CriticalDatabaseErrorException exception) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected database error, missing static information", exception.getLocalizedMessage());
        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiError> handleException(BadCredentialsException exception) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Invalid credentials",
                exception.getLocalizedMessage());
        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception) {
        logger.error(exception.toString());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred",
                exception.getLocalizedMessage());
        return new ResponseEntity<ApiError>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
