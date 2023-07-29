package com.example.PaymentGateway.services;

import com.example.PaymentGateway.models.User;
import com.example.PaymentGateway.repository.UserRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepsoitory userRepsoitory;

    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        userRepsoitory.save(user);
        return user;
    }


}
