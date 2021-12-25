package com.upgrad.bookingservice.service;

import com.upgrad.bookingservice.dto.BookingDTO;
import com.upgrad.bookingservice.dto.PaymentDTO;
import com.upgrad.bookingservice.entity.BookingInfoEntity;

import java.util.zip.DataFormatException;

public interface BookingService {
    BookingInfoEntity createBooking(BookingInfoEntity bookingInfoEntity);
    BookingInfoEntity getBookingById(int bookingId);
    BookingInfoEntity updateTransaction(int transactionId, PaymentDTO paymentDTO);
}
