package com.upgrad.bookingservice.exception;

public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String invalid_mode_of_payment)
    {
        super(invalid_mode_of_payment);
    }
}
