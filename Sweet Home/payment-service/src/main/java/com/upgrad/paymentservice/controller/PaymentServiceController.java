package com.upgrad.paymentservice.controller;

import com.upgrad.paymentservice.dto.TransactionDTO;
import com.upgrad.paymentservice.dto.TransactionDetailsDTO;
import com.upgrad.paymentservice.entity.TransactionDetailsEntity;
import com.upgrad.paymentservice.service.PaymentService;
import com.upgrad.paymentservice.utils.POJOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentServiceController {

    @Autowired
    PaymentService _paymentService;

    public PaymentServiceController(PaymentService paymentService){
        _paymentService=paymentService;
    }

    @PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> makePayment(@RequestBody TransactionDTO transactionDTO){
        TransactionDetailsEntity transactionDetailsEntity=POJOConverter.transactionDtoToEntity(transactionDTO);
        Integer transactionId=_paymentService.makeATransaction(transactionDetailsEntity);
        return new ResponseEntity<>(transactionId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/transaction/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetailsDTO> makePayment(@PathVariable int transactionId){
        TransactionDetailsEntity transactionDetailsEntity =_paymentService.getTransactionById(transactionId);
        TransactionDetailsDTO transactionDetailsDTO=POJOConverter.transactionDetailsEntitytoDTO(transactionDetailsEntity);
        return new ResponseEntity<>(transactionDetailsDTO, HttpStatus.OK);
    }

}
