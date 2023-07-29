package com.example.PaymentGateway.controllers;

import com.example.PaymentGateway.models.Transaction;
import com.example.PaymentGateway.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/{userId}/{amount}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable String userId, @PathVariable Integer amount) {
        Transaction transaction = transactionService.addTransaction(userId, amount);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @DeleteMapping("/failed-transactions")
    public ResponseEntity<String> deleteFailedTransactions() {
        transactionService.deleteFailedTransactions();
        return new ResponseEntity<>("Deleted all failed transactions", HttpStatus.OK);
    }


}
