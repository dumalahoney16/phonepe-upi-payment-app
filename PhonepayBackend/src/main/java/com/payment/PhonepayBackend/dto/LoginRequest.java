package com.payment.PhonepayBackend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String upiId;
    private String pin;
}