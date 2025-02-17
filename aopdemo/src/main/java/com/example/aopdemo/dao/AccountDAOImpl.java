package com.example.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

    @Override
    public void addAccount() {

        System.out.println(getClass() + ": doing db work, adding account");
    }
}
