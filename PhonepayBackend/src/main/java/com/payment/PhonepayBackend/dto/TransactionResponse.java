package com.payment.PhonepayBackend.dto;

import lombok.Data;

@Data
public class TransactionResponse {

    private String senderUpi;
    private String receiverUpi;
    private double amount;
    private String status;
    private String transactionDate;

}