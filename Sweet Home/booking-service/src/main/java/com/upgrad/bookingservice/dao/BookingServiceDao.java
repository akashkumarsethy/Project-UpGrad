package com.upgrad.bookingservice.dao;

import com.upgrad.bookingservice.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingServiceDao extends JpaRepository<BookingInfoEntity, Integer> {
}
