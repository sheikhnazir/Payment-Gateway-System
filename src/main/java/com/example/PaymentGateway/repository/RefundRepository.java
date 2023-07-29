package com.example.PaymentGateway.repository;

import com.example.PaymentGateway.models.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Integer> {
}
