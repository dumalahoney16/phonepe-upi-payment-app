package com.payment.PhonepayBackend.repository;

import com.payment.PhonepayBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUpiId(String upiId);

}