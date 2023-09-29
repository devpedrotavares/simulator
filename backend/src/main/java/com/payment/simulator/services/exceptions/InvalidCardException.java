package com.payment.simulator.services.exceptions;


public class InvalidCardException extends RuntimeException {
    public InvalidCardException() {
        super();
    }

    public InvalidCardException (String message) {
        super(message);
    }
}
