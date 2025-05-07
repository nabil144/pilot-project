package com.example.walletExercicedemo.request;
import java.math.BigDecimal;

public class TransactionRequest {

    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;

    public TransactionRequest(Long senderId, Long receiverId, BigDecimal amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }
    public Long getSenderId() {
        return senderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getReceiverId() {
        return receiverId;
    }

}

