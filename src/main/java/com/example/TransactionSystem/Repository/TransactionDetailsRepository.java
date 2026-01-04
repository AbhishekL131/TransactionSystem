package com.example.TransactionSystem.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.TransactionSystem.Models.TransactionDetails;

@Repository
public interface TransactionDetailsRepository extends MongoRepository<TransactionDetails,ObjectId>{
    Optional<TransactionDetails> findBySenderIdAndReceiverId(String senderId,String receiverId);
    Optional<TransactionDetails> findByTransactionId(String transactionId);
}
