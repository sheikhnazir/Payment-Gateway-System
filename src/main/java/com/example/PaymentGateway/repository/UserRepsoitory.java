package com.example.PaymentGateway.repository;

import com.example.PaymentGateway.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsoitory extends JpaRepository<User, Integer> {

    User findByUserId(String userId);
}
