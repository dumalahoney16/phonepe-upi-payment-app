package com.payment.PhonepayBackend.service;

import com.payment.PhonepayBackend.dto.*;
import com.payment.PhonepayBackend.model.*;
import com.payment.PhonepayBackend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    // ======================
    // SEND MONEY
    // ======================
    public String sendMoney(SendMoneyRequest request) {

        // Find sender
        User sender = userRepository.findByUpiId(request.getSenderUpi())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        // Find receiver
        User receiver = userRepository.findByUpiId(request.getReceiverUpi())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        // PIN validation
        if (!String.valueOf(sender.getPin()).equals(request.getPin())) {
            throw new RuntimeException("Invalid PIN");
        }

        // Fetch wallets
        Wallet senderWallet = walletRepository.findByUser(sender);
        Wallet receiverWallet = walletRepository.findByUser(receiver);

        if (senderWallet == null) {
            throw new RuntimeException("Sender wallet not found");
        }

        if (receiverWallet == null) {
            throw new RuntimeException("Receiver wallet not found");
        }

        // Check balance
        if (senderWallet.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct sender balance
        senderWallet.setBalance(senderWallet.getBalance() - request.getAmount());

        // Add receiver balance
        receiverWallet.setBalance(receiverWallet.getBalance() + request.getAmount());

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        // Save transaction
        Transaction transaction = new Transaction();

        transaction.setSenderUpi(sender.getUpiId());
        transaction.setSenderName(sender.getName());

        transaction.setReceiverUpi(receiver.getUpiId());
        transaction.setReceiverName(receiver.getName());

        transaction.setAmount(request.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

        transactionRepository.save(transaction);

        return "Money Sent Successfully";
    }


    // ======================
    // TRANSACTION HISTORY
    // ======================
    public List<TransactionHistory> getTransactionHistory(String upiId) {

        List<Transaction> transactions =
                transactionRepository.findBySenderUpiOrReceiverUpi(upiId, upiId);

        List<TransactionHistory> historyList = new ArrayList<>();

        for (Transaction t : transactions) {

            TransactionHistory history = new TransactionHistory();

            history.setSenderName(t.getSenderName());
            history.setReceiverName(t.getReceiverName());
            history.setAmount(t.getAmount());
            history.setStatus(t.getStatus());
            history.setTransactionDate(t.getTransactionDate());

            historyList.add(history);
        }

        return historyList;
    }
}