package com.payment.PhonepayBackend.repository;

import com.payment.PhonepayBackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderUpiOrReceiverUpi(String senderUpi, String receiverUpi);

}