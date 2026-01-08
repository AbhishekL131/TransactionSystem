package com.example.TransactionSystem.Service;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TransactionSystem.Manager.UserLockManager;
import com.example.TransactionSystem.Models.Transaction;
import com.example.TransactionSystem.Models.TransactionDetails;
import com.example.TransactionSystem.Models.Wallet;
import com.example.TransactionSystem.Repository.TransactionDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepo;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserLockManager lockManager;

    public boolean makeTransaction(Transaction transaction){
       String senderId = transaction.getSenderId();
       String receiverId = transaction.getReceiverId();
       long amount = transaction.getAmount();

       Optional<Wallet> senderWalletOpt = walletService.getWalletByUserId(senderId);
       Optional<Wallet> receiverWalletOpt = walletService.getWalletByUserId(receiverId);

       if(senderWalletOpt.isEmpty() || receiverWalletOpt.isEmpty()){
        log.info("sender or receiver wallet does not exist");
        return false;
       }

       Wallet SenderWallet = senderWalletOpt.get();
       Wallet ReceiverWallet = receiverWalletOpt.get();

       if(SenderWallet.getBalance() < amount){
        log.info("insufficient funds in sender account");
        return false;
       }

       SenderWallet.setBalance(SenderWallet.getBalance()-amount);
       ReceiverWallet.setBalance(ReceiverWallet.getBalance()+amount);

       walletService.createWallet(SenderWallet);
       walletService.createWallet(ReceiverWallet);

       TransactionDetails tdt = new TransactionDetails();
       tdt.setSenderId(SenderWallet.getUserId());
       tdt.setReceiverId(ReceiverWallet.getUserId());
       tdt.setAmount(amount);
       tdt.setStatus("SUCCESSFUL");

       transactionDetailsRepo.save(tdt);

       return true;
    }

    public Optional<TransactionDetails> getBySenderAndReceiverId(String senderId,String receiverId){
        return transactionDetailsRepo.findBySenderIdAndReceiverId(senderId,receiverId);
    }
}
