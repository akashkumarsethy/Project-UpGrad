package com.upgrad.bookingservice.util;

import com.upgrad.bookingservice.dto.BookingDTO;
import com.upgrad.bookingservice.entity.BookingInfoEntity;
import com.upgrad.bookingservice.exception.DateFormatException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class POJOConverter {


    @Autowired

    public static BookingServiceUtils bookingServiceUtils;

    public static BookingInfoEntity covertUserDTOToEntity(BookingDTO bookingDTO) throws DateFormatException {
        BookingInfoEntity bookingInfoEntity=new BookingInfoEntity();

        bookingInfoEntity.setAadharNumber(bookingDTO.getAadharNumber());
        bookingInfoEntity.setFromDate(bookingServiceUtils.validateDate(bookingDTO.getFromDate()));
        bookingInfoEntity.setToDate(bookingServiceUtils.validateDate(bookingDTO.getToDate()));
        bookingInfoEntity.setNumOfRooms(bookingDTO.getNumOfRooms());

        return bookingInfoEntity;
    }

}
