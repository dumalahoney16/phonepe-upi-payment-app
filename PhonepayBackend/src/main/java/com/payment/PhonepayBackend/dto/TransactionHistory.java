package com.payment.PhonepayBackend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransactionHistory {

    private String senderName;
    private String receiverName;
    private double amount;
    private String status;
    private LocalDateTime transactionDate;

}