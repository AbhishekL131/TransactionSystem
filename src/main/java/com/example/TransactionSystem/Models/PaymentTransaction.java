package com.example.TransactionSystem.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection="PaymentTransaction")
public class PaymentTransaction {

    @Id
    private ObjectId transactionId;
    @NonNull
    private String userId;
    @NonNull
    private long amount;
    @NonNull
    private String status;
}
