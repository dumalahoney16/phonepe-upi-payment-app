package com.payment.PhonepayBackend.dto;

import lombok.Data;

@Data
public class WalletBalanceResponse {

    private String upiId;
    private double balance;

}