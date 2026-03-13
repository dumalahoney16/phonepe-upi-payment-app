package com.payment.PhonepayBackend.repository;

import com.payment.PhonepayBackend.model.User;
import com.payment.PhonepayBackend.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByUser(User user);

}