package com.example.TransactionSystem.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransactionSystem.Models.Wallet;
import com.example.TransactionSystem.Service.WalletService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wallet")
@Slf4j
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@RequestBody Wallet wallet){
        String userId = wallet.getUserId();
        Optional<Wallet> existingWallet = walletService.getWalletByUserId(userId);
        if(existingWallet.isPresent()){
            log.info("wallet already exists for "+userId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            walletService.createWallet(wallet);
        }
        return new ResponseEntity<>(wallet,HttpStatus.OK);
    }

    @GetMapping("/getBalance")
    public ResponseEntity<Long> getTotalBalance(){
        long totalBalance = walletService.getTotalBalance();
        return new ResponseEntity<>(totalBalance,HttpStatus.OK);
    }
}
