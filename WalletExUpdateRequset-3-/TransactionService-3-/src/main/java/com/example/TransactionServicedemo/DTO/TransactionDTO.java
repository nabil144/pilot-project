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

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionDTO (){}


    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
