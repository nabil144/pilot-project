package com.example.walletExercicedemo.request;

import java.math.BigDecimal;

public class TransferRequest {
    private Long receiverId;
    private BigDecimal amount;

    public TransferRequest(Long receiverId, BigDecimal amount) {
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public Long getReceiverId() {
        return receiverId;
    }


    public BigDecimal getAmount() {
        return amount;
    }

}
