package com.example.TransactionSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TransactionSystem.Models.Wallet;
import com.example.TransactionSystem.Repository.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepo;

    public void createWallet(Wallet wallet){
        walletRepo.save(wallet);
    }

    public Optional<Wallet> getWalletByUserId(String userId){
        return walletRepo.findByUserId(userId);
    }

    public List<Wallet> getAllWallets(){
        return walletRepo.findAll();
    }

    public long getTotalBalance(){
        List<Wallet> wallets = walletRepo.findAll();
        long totalBalance = wallets
        .stream()
        .mapToLong(wallet -> wallet.getBalance())
        .sum();

        return totalBalance;
    }

}
