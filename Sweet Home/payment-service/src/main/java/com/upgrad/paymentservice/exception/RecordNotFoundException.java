package com.upgrad.paymentservice.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String s) {
        super(s);
    }
}
