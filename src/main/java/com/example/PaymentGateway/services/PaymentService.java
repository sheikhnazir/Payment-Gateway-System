package com.example.PaymentGateway.services;

import com.example.PaymentGateway.enums.transactionstatus;
import com.example.PaymentGateway.models.Refund;
import com.example.PaymentGateway.models.Transaction;
import com.example.PaymentGateway.models.User;
import com.example.PaymentGateway.repository.RefundRepository;
import com.example.PaymentGateway.repository.TransactionRepository;
import com.example.PaymentGateway.repository.UserRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    RefundRepository refundRepository;

    @Autowired
    UserRepsoitory userRepsoitory;
    public double getTotalAmountForUser(String userId) {
        User user = userRepsoitory.findByUserId(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        List<Transaction> transactionList = user.getTransactions();

        return transactionList.stream()
                .filter(t -> t.getStatus() == transactionstatus.SUCCESS)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public void createRefund(String userId, String transactionId) {
        User user = userRepsoitory.findByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Transaction transaction = transactionRepository.findByTransactionIdAndUser(transactionId, user);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction not found");
        }

        if (transaction.getStatus() == transactionstatus.FAILED && transaction.isAmountDeducted()) {
            Refund refund = new Refund();
            refund.setAmount(transaction.getAmount());
            refund.setUser(user);
            refund.setTransaction(transaction);
            user.getRefunds().add(refund);
            userRepsoitory.save(user);
            //add the refund to a list of refunds or save it in a separate RefundRepository.
            // Save the refund entity using a proper JPA repository.
            refundRepository.save(refund);


        }
    }
}
