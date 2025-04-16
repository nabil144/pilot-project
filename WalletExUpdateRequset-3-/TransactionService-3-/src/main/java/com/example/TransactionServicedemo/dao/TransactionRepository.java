package com.example.TransactionServicedemo.dao;

//import jakarta.transaction.Transaction;
//import org.springframework.data.jpa.repository.JpaRepository;
import com.example.TransactionServicedemo.TransactionServicedemoApplication;
import com.example.TransactionServicedemo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findBySenderId(Long senderId);
    List<Transaction> findByReceiverId(Long receiverId);
    //public double getWalletBalance();
}
