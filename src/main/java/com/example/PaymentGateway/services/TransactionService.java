package com.example.PaymentGateway.services;

import com.example.PaymentGateway.enums.transactionstatus;
import com.example.PaymentGateway.models.Transaction;
import com.example.PaymentGateway.models.User;
import com.example.PaymentGateway.repository.TransactionRepository;
import com.example.PaymentGateway.repository.UserRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    UserRepsoitory userRepsoitory;
    @Autowired
    TransactionRepository transactionRepository;

    public Transaction addTransaction(String userId, Integer amount) {
        User user = userRepsoitory.findByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setAmount(amount);
        transaction.setStatus(transactionstatus.SUCCESS);
        transaction.setAmountDeducted(true);
        transaction.setTime(LocalDateTime.now());
        transaction.setUser(user);

        return transactionRepository.save(transaction);
    }

    public void deleteFailedTransactions() {
        // Assuming you have a separate method in the TransactionRepository to delete failed transactions
        transactionRepository.deleteByStatus(transactionstatus.FAILED);
    }
}
