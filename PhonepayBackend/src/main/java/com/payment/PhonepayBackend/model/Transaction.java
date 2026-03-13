package com.payment.PhonepayBackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderUpi;
    private String senderName;
    private String receiverUpi;
    private String receiverName;
    private double amount;
    private LocalDateTime transactionDate;
    private String status;
}