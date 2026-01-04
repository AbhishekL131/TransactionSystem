package com.example.TransactionSystem.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transaction {
    private String senderId;
    private String receiverId;
    private long amount;
}
