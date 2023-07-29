package com.example.PaymentGateway.dto.requestDTO;

import lombok.Data;

@Data
public class AddUserDto {

    private String Name;
    private String Email;
    private String AccountNumber;
}
