package com.payment.PhonepayBackend.dto;

import lombok.Data;

@Data
public class AddMoneyRequest {

    private String upiId;
    private double amount;

}