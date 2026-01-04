package com.example.TransactionSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransactionSystem.Models.Transaction;
import com.example.TransactionSystem.Service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody Transaction transaction){
        boolean transactionStatus = transactionService.makeTransaction(transaction);
        if(!transactionStatus){
            return ResponseEntity.ok("transaction failed");
        }
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }
}
