package com.payment.simulator.resources.exceptions;

import com.payment.simulator.services.exceptions.InvalidCardException;
import com.payment.simulator.services.exceptions.InvalidPaymentException;
import com.payment.simulator.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(InvalidCardException.class)
    public ResponseEntity<StandardError> invalidCardExceptionHandler(InvalidCardException e) {
        String error = "Invalid Card Form";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(status.value(), error, e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<StandardError> invalidPaymentExceptionHandler(InvalidPaymentException e) {
        String error = "Invalid Payment";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(status.value(), error, e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

}
