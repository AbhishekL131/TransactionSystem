package com.example.TransactionSystem.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection="wallet")
public class Wallet {
    @Id
    private String userId;
    @NonNull
    private String userName;
    @NonNull
    private long balance;
}
