package com.upgrad.bookingservice.controller;

import com.upgrad.bookingservice.dto.BookingDTO;
import com.upgrad.bookingservice.dto.PaymentDTO;
import com.upgrad.bookingservice.entity.BookingInfoEntity;
import com.upgrad.bookingservice.service.BookingService;
import com.upgrad.bookingservice.util.POJOConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.zip.DataFormatException;

@RestController
@RequestMapping(value = "/hotel")
public class BookingServiceController {

    @Autowired
    private BookingService _bookingService;

    public BookingServiceController(BookingService bookingService ){
        _bookingService=bookingService;
    }

    @PostMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> createBooking(@RequestBody BookingDTO bookingDTO){
        BookingInfoEntity bookingInfoEntity= POJOConverter.covertUserDTOToEntity(bookingDTO);
        BookingInfoEntity savedBooking = _bookingService.createBooking(bookingInfoEntity);
        return new ResponseEntity(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping(value = "/booking/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> getBookingDetails(@PathVariable int bookingId){
        BookingInfoEntity bookingInfoEntity = _bookingService.getBookingById(bookingId);
        return new ResponseEntity(bookingInfoEntity, HttpStatus.OK);
    }

    @PostMapping(value = "/booking/{bookingId}/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> updateTransactionId(@PathVariable(name="bookingId") int bookingId, @RequestBody PaymentDTO paymentDTO){
        BookingInfoEntity bookingInfoEntity = _bookingService.updateTransaction(bookingId, paymentDTO);
        return new ResponseEntity<>(bookingInfoEntity, HttpStatus.CREATED);
    }

}
