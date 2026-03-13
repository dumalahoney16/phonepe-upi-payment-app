package com.payment.PhonepayBackend.service;

import com.payment.PhonepayBackend.dto.LoginRequest;
import com.payment.PhonepayBackend.dto.UserProfileResponse;
import com.payment.PhonepayBackend.dto.UserRegistrationRequest;
import com.payment.PhonepayBackend.model.User;
import com.payment.PhonepayBackend.model.Wallet;
import com.payment.PhonepayBackend.repository.UserRepository;
import com.payment.PhonepayBackend.repository.WalletRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;


    // =========================
    // REGISTER USER
    // =========================
    public String registerUser(UserRegistrationRequest request) {

        if (userRepository.findByUpiId(request.getUpiId()).isPresent()) {
            return "UPI already exists";
        }

        User user = new User();
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUpiId(request.getUpiId());
        user.setPin(request.getPin());

        userRepository.save(user);

        // create wallet for user
        Wallet wallet = new Wallet();
        wallet.setBalance(0.0);
        wallet.setUser(user);

        walletRepository.save(wallet);

        return "User registered successfully";
    }


    // =========================
    // LOGIN USER
    // =========================
    public String login(LoginRequest request) {

        User user = userRepository
                .findByUpiId(request.getUpiId())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!user.getPin().equals(request.getPin())) {
            return "Invalid PIN";
        }

        return "Login successful";
    }


    // =========================
    // GET USER PROFILE + WALLET
    // =========================
    public UserProfileResponse getUserProfile(String upiId) {

        User user = userRepository
                .findByUpiId(upiId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        Wallet wallet = walletRepository.findByUser(user);

        UserProfileResponse response = new UserProfileResponse();

        response.setName(user.getName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setUpiId(user.getUpiId());

        if (wallet != null) {
            response.setBalance(wallet.getBalance());
        }

        return response;
    }

}