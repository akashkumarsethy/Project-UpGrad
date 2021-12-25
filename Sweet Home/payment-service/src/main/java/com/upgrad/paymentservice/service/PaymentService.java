package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.dto.TransactionDTO;
import com.upgrad.paymentservice.entity.TransactionDetailsEntity;

public interface PaymentService {
    Integer makeATransaction(TransactionDetailsEntity transactionDetailsEntity);
    TransactionDetailsEntity getTransactionById(int transactionId);
}
