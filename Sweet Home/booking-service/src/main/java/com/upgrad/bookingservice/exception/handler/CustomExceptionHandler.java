package com.upgrad.bookingservice.exception.handler;

import com.upgrad.bookingservice.exception.DateFormatException;
import com.upgrad.bookingservice.exception.DateRangeException;
import com.upgrad.bookingservice.exception.InvalidPaymentException;
import com.upgrad.bookingservice.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static String DATE_FORMAT_ERROR = "DATE_FORMAT_ERROR";
    public static String DATE_RANGE_ERROR = "DATE_RANGE_ERROR";
    public static String NO_RECORDS_FOUND = "NO_RECORDS_FOUND";

    @ExceptionHandler({DateFormatException.class, DateRangeException.class, RecordNotFoundException.class, InvalidPaymentException.class})
    public ResponseEntity<Object> handleUserGeneratedException(Exception e){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", e.getLocalizedMessage() );
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

}
