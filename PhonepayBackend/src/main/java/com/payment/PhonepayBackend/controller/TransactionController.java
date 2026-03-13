package com.payment.PhonepayBackend.controller;

import com.payment.PhonepayBackend.dto.*;
import com.payment.PhonepayBackend.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Send money")
    @PostMapping("/send")
    public String sendMoney(@RequestBody SendMoneyRequest request) {
        return transactionService.sendMoney(request);
    }

    @Operation(summary = "Get transaction history")
    @GetMapping("/history/{upiId}")
    public List<TransactionHistory> getHistory(@PathVariable String upiId) {
        return transactionService.getTransactionHistory(upiId);
    }
}