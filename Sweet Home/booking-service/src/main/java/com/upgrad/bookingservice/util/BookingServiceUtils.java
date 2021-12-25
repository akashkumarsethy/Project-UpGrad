package com.upgrad.bookingservice.util;

import com.upgrad.bookingservice.exception.DateFormatException;
import com.upgrad.bookingservice.exception.DateRangeException;
import com.upgrad.bookingservice.exception.InvalidPaymentException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;

@Component
public class BookingServiceUtils {

    public static String getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        //Convert the array list into a string
        return String.join(",", numberList);

    }

    public static LocalDateTime validateDate(String date) throws DateFormatException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate ld = LocalDate.parse(date, dateFormatter);
            return ld.atStartOfDay();
        } catch(DateTimeParseException e){
            throw new DateFormatException("Invalid Date Format : YYYY-MM-DD");
        }

    }

    public static void validateFromToDate(LocalDateTime fromDate, LocalDateTime toDate) throws DateRangeException {
        System.out.println("from " + fromDate +  " To " + toDate);

        if (toDate.compareTo(fromDate) < 0){
            throw new DateRangeException("From Date needs to be Before the To Date");
        }
    }

    public long getNumberOfDays(LocalDateTime fromDate, LocalDateTime toDate) {
        return Duration.between(fromDate, toDate).toDays();
    }

    public void checkPaymentMethod(String paymentMode) throws InvalidPaymentException {
        if (!paymentMode.equals("CARD") && !paymentMode.equals("UPI"))
            throw new InvalidPaymentException("Invalid mode of payment");

    }
}
