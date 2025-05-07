package com.example.TransactionServicedemo.Handler;

public class TransactionNotFoundException extends RuntimeException{
    // Constructor to pass the message to the superclass (RuntimeException)
    public TransactionNotFoundException(String message) {
        super(message);
    }

}
