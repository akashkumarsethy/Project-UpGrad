package com.upgrad.bookingservice.service;

import com.upgrad.bookingservice.dao.BookingServiceDao;
import com.upgrad.bookingservice.dto.PaymentDTO;
import com.upgrad.bookingservice.entity.BookingInfoEntity;
import com.upgrad.bookingservice.exception.DateRangeException;
import com.upgrad.bookingservice.exception.InvalidPaymentException;
import com.upgrad.bookingservice.exception.RecordNotFoundException;
import com.upgrad.bookingservice.util.BookingServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingServiceDao _bookingServiceDao;

    @Autowired
    private BookingServiceUtils bookingServiceUtils;

    @Autowired
    RestTemplate restTemplate;

    @Value("${paymentApp.url}")
    private String paymentServiceUrl;


    public BookingServiceImpl(BookingServiceDao bookingServiceDao){
        _bookingServiceDao=bookingServiceDao;
    }

    @Override
    public BookingInfoEntity createBooking(BookingInfoEntity bookingInfoEntity)throws DateRangeException {

        BookingInfoEntity bookingToBeSaved = new BookingInfoEntity();
        //Setting Aadhar Number to Booking Info Entity
        bookingToBeSaved.setAadharNumber(bookingInfoEntity.getAadharNumber());

        //Setting From Date to Booking Info Entity
        bookingToBeSaved.setFromDate(bookingInfoEntity.getFromDate());

        //Setting To Date to Booking Info Entity
        bookingToBeSaved.setToDate(bookingInfoEntity.getToDate());

        // Validating whether from date is less than the to date
        bookingServiceUtils.validateFromToDate(bookingToBeSaved.getFromDate(), bookingToBeSaved.getToDate());

        // Getting the number of days
        int numberOfDays = (int)bookingServiceUtils.getNumberOfDays(bookingToBeSaved.getFromDate(), bookingToBeSaved.getToDate());

        //Setting Number of rooms to Booking Info Entity
        bookingToBeSaved.setNumOfRooms(bookingInfoEntity.getNumOfRooms());

        //Setting the random generated Room numbers to Booking Info Entity
        bookingToBeSaved.setRoomNumbers(bookingServiceUtils.getRandomNumbers(bookingInfoEntity.getNumOfRooms()));

        //Setting Room price to Booking Info Entity
        bookingToBeSaved.setRoomPrice(1000 * bookingInfoEntity.getNumOfRooms() * numberOfDays);

        //Setting Booking Date to Booking Info Entity
        bookingToBeSaved.setBookedOn(LocalDateTime.now());

        //Saving Booking Information to Database and returning saved entity
        return _bookingServiceDao.save(bookingToBeSaved);
    }

    @Override
    public BookingInfoEntity getBookingById(int bookingId) throws RecordNotFoundException {
        //Searching Booking Information in Database by using bookingId
        return _bookingServiceDao.findById(bookingId).orElseThrow(()->new RecordNotFoundException("Booking ID does not Exists"));
    }

    @Override
    public BookingInfoEntity updateTransaction(int bookingId, PaymentDTO paymentDTO) throws RecordNotFoundException, InvalidPaymentException {
        //Validating the payment method
        bookingServiceUtils.checkPaymentMethod(paymentDTO.getPaymentMode());
        //Payment service Endpoint1 URL
        String url = this.paymentServiceUrl;
        //Checking If the Booking details exists in database or not
        Optional<BookingInfoEntity> bookingInfoOptional = _bookingServiceDao.findById(bookingId);
        if(bookingInfoOptional.isPresent()) {
            BookingInfoEntity bookingInfo = bookingInfoOptional.get();
            //Making Payment using Payment Service EndPoint1 using Synchronous Communication and getting Transaction ID
            int trancationId = restTemplate.postForObject(url,paymentDTO, Integer.class);
            bookingInfo.setTransactionId(trancationId);
            _bookingServiceDao.save(bookingInfo);
            String message = "Booking confirmed for user with aadhaar number: " + bookingInfo.getAadharNumber() +
                    "    |    " + "Here are the booking details:    " + bookingInfo.toString();
            System.out.println(message);
            return bookingInfo;
        }else {
            throw new InvalidPaymentException("Invalid Booking Id");
        }
    }
}
