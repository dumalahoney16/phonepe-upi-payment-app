package com.payment.PhonepayBackend.service;

import com.payment.PhonepayBackend.dto.*;
import com.payment.PhonepayBackend.model.*;
import com.payment.PhonepayBackend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;


    // =========================
    // ADD MONEY
    // =========================
    public String addMoney(AddMoneyRequest request) {

        User user = userRepository.findByUpiId(request.getUpiId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user);

        if (wallet == null) {
            return "Wallet not found";
        }

        wallet.setBalance(wallet.getBalance() + request.getAmount());

        walletRepository.save(wallet);

        return "Money added successfully";
    }


    // =========================
    // GET WALLET BALANCE
    // =========================
    public WalletBalanceResponse getBalance(String upiId) {

        User user = userRepository.findByUpiId(upiId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user);

        if (wallet == null) {
            return null;
        }

        WalletBalanceResponse response = new WalletBalanceResponse();

        response.setUpiId(user.getUpiId());
        response.setBalance(wallet.getBalance());

        return response;
    }
}