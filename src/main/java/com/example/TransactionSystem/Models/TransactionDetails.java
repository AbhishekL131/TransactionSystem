package com.example.TransactionSystem.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection="TransactionDetails")
public class TransactionDetails {

    @Id
    private ObjectId transactionId;
    @NonNull
    private String senderId;
    @NonNull
    private String receiverId;
    @NonNull
    private long amount;
    @NonNull
    private String status;
}
