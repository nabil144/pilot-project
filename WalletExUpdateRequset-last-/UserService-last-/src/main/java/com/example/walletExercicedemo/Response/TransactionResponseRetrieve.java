package com.example.walletExercicedemo.Response;

import java.math.BigDecimal;

public class TransactionResponseRetrieve {

    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
    private String status;

    public TransactionResponseRetrieve(Long senderId, Long receiverId, BigDecimal amount, String status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.status = status;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
