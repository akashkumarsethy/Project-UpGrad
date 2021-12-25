package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.dao.PaymentServiceDao;
import com.upgrad.paymentservice.entity.TransactionDetailsEntity;
import com.upgrad.paymentservice.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentServiceDao _paymentServiceDao;

    public PaymentServiceImpl(PaymentServiceDao paymentServiceDao){
        _paymentServiceDao=paymentServiceDao;
    }

    @Override
    public Integer makeATransaction(TransactionDetailsEntity transactionDetailsEntity) {
        //Saving the transaction details to Database
        TransactionDetailsEntity savedData = _paymentServiceDao.save(transactionDetailsEntity);
        //Returning the transactionId
        return savedData.getTransactionId();

    }

    @Override
    public TransactionDetailsEntity getTransactionById(int transactionId) throws RecordNotFoundException {
       //Getting the transaction details from Databse using transactionId
        return  _paymentServiceDao.findById(transactionId).orElseThrow(
                ()-> new RecordNotFoundException("Invalid Transaction ID")
        );
    }
}
