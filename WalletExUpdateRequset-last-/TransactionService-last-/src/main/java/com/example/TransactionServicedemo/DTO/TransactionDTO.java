package com.example.TransactionServicedemo.DTO;

public class TransactionDTO {

    private Long senderId;
    private Long receiverId;
    private Double amount;
    private String status;

    public TransactionDTO(Long senderId, Long receiverId, Double amount,String status) {

        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.status=status;
    }


    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public Double getAmount() {
        return amount;
    }

}
