package com.example.PaymentGateway.repository;

import com.example.PaymentGateway.enums.transactionstatus;
import com.example.PaymentGateway.models.Transaction;
import com.example.PaymentGateway.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    void deleteByStatus(transactionstatus failed);

    Transaction findByTransactionIdAndUser(String transactionId, User user);
}
