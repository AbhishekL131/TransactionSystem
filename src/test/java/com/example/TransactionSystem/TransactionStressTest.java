package com.example.TransactionSystem;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TransactionSystem.Models.Transaction;
import com.example.TransactionSystem.Service.TransactionService;


@SpringBootTest
public class TransactionStressTest {

    @Autowired
    private TransactionService transactionService;

   

    private static final int Threads = 1000;

    @Test
    public void stressTestTransactions() throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(50);
        CountDownLatch latch = new CountDownLatch(Threads);

        List<String> users = List.of(
            "US1034","US1039","US1045","US1043","US1049"
        );

        long startTime = System.currentTimeMillis();
        for(int i=0; i<Threads; i++){
            executor.submit(() -> {
                try{
                    String sender = users.get(ThreadLocalRandom.current().nextInt(users.size()));
                    String receiver = users.get(ThreadLocalRandom.current().nextInt(users.size()));

                    if(sender.equals(receiver)) return;

                    Transaction tx = new Transaction();
                    tx.setSenderId(sender);
                    tx.setReceiverId(receiver);
                    tx.setAmount(10);

                    transactionService.makeTransaction(tx);

                }catch(Exception e){
                    
                }finally{
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        long endTime = System.currentTimeMillis();
        
        System.out.println("total time taken : "+(startTime-endTime)+"millis");
        System.out.println("stress test completed");
    }
}
