package com.upgrad.paymentservice.dto;

public class TransactionDTO {

    private int bookingId;
    private String paymentMode;
    private String upiId;
    private String cardNumber;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public TransactionDTO(int bookingId, String paymentMode, String upiId, String cardNumber) {
        this.bookingId = bookingId;
        this.paymentMode = paymentMode;
        this.upiId = upiId;
        this.cardNumber = cardNumber;
    }

    public TransactionDTO() {}

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "bookingId=" + bookingId +
                ", paymentMode='" + paymentMode + '\'' +
                ", upiId='" + upiId + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }

}
