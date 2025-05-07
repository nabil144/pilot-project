package com.example.TransactionServicedemo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="sender_id")
    private Long senderId;

    @Column(name = "receiver_id")
    private Long receiverId;


    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "timestamp")
    private Timestamp timestamp;


    @Column(name = "status")
    private String status;



   public Transaction(){

   }

    public Transaction(Long id, Long senderId, Long receiverId, BigDecimal amount, Timestamp timestamp, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
