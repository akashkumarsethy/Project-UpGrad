package com.upgrad.paymentservice.utils;

import com.upgrad.paymentservice.dto.TransactionDTO;
import com.upgrad.paymentservice.dto.TransactionDetailsDTO;
import com.upgrad.paymentservice.entity.TransactionDetailsEntity;

public class POJOConverter {
    public static TransactionDetailsEntity transactionDtoToEntity(TransactionDTO transactionDTO){
        TransactionDetailsEntity transactionDetailsEntity=new TransactionDetailsEntity();
        transactionDetailsEntity.setBookingId(transactionDTO.getBookingId());
        transactionDetailsEntity.setPaymentMode(transactionDTO.getPaymentMode());
        transactionDetailsEntity.setUpiId(transactionDTO.getUpiId());
        transactionDetailsEntity.setCardNumber(transactionDTO.getCardNumber());
        return transactionDetailsEntity;
    }

    public static TransactionDetailsDTO transactionDetailsEntitytoDTO(TransactionDetailsEntity transactionDetailsEntity){
        TransactionDetailsDTO transactionDetailsDTO=new TransactionDetailsDTO();
        transactionDetailsDTO.setTransactionId(transactionDetailsEntity.getTransactionId());
        transactionDetailsDTO.setBookingId(transactionDetailsEntity.getBookingId());
        transactionDetailsDTO.setPaymentMode(transactionDetailsEntity.getPaymentMode());
        transactionDetailsDTO.setUpiId(transactionDetailsEntity.getUpiId());
        transactionDetailsDTO.setCardNumber(transactionDetailsEntity.getCardNumber());
        return transactionDetailsDTO;
    }

}
