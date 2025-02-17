package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

    @Override
    public void addAccount(Account account) {

        System.out.println(getClass() + ": doing db work, adding account");
    }
}
