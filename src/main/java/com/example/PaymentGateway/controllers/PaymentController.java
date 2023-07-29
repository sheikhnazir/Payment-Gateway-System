package com.example.PaymentGateway.controllers;

import com.example.PaymentGateway.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/total-amount/{userId}")
    public ResponseEntity<Double> getTotalAmountForUser(@PathVariable String userId) {
        double totalAmount = paymentService.getTotalAmountForUser(userId);
        return new ResponseEntity<>(totalAmount, HttpStatus.OK);
    }

    @PostMapping("/{userId}/{transactionId}")
    public ResponseEntity<String> createRefund(
            @PathVariable String userId, @PathVariable String transactionId) {
        paymentService.createRefund(userId, transactionId);
        return new ResponseEntity<>("Refund created", HttpStatus.CREATED);
    }
}
