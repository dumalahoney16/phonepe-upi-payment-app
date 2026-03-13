package com.payment.PhonepayBackend.dto;

import lombok.Data;

@Data
public class SendMoneyRequest {

    private String senderUpi;
    private String receiverUpi;
    private double amount;
    private String pin;
}