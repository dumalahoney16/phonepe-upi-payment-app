package com.payment.PhonepayBackend.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {

    private String name;
    private String phoneNumber;
    private String upiId;
    private String pin;

}