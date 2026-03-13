package com.payment.PhonepayBackend.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Wallet")
public class Wallet {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private double balance;
     @OneToOne
     @JoinColumn(name = "user_id")
     private User user;
}