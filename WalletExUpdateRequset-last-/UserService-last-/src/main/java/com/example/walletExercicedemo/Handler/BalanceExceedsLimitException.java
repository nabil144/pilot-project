package com.example.walletExercicedemo.Handler;

public class BalanceExceedsLimitException extends RuntimeException{
    public BalanceExceedsLimitException(String message) {
        super(message);
    }
}
