package com.upgrad.paymentservice.dao;

import com.upgrad.paymentservice.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentServiceDao extends JpaRepository<TransactionDetailsEntity,Integer> {
}
