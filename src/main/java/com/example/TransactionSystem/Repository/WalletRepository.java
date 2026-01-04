package com.example.TransactionSystem.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.TransactionSystem.Models.Wallet;

@Repository
public interface WalletRepository extends MongoRepository<Wallet,String>{
    Optional<Wallet> findByUserId(String userId);
}
