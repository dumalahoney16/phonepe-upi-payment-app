package com.payment.PhonepayBackend.controller;

import com.payment.PhonepayBackend.dto.*;
import com.payment.PhonepayBackend.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Operation(summary = "Add money to wallet")
    @PostMapping("/add")
    public String addMoney(@RequestBody AddMoneyRequest request) {
        return walletService.addMoney(request);
    }

    @Operation(summary = "Check wallet balance")
    @GetMapping("/balance/{upiId}")
    public WalletBalanceResponse getBalance(@PathVariable String upiId) {
        return walletService.getBalance(upiId);
    }
}