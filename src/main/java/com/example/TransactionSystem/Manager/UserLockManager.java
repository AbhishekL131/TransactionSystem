package com.example.TransactionSystem.Manager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

@Component
public class UserLockManager {
    private static ConcurrentHashMap<String,ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public ReentrantLock getLock(String userId){
        return lockMap.computeIfAbsent(userId,id -> new ReentrantLock());
    }
}
