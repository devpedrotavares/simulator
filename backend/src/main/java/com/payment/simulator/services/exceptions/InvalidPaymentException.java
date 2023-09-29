package com.payment.simulator.services.exceptions;

public class InvalidPaymentException extends RuntimeException {

    public InvalidPaymentException() {

    }

    public InvalidPaymentException(String message) {
        super(message);
    }
}
