package com.example.aopdemo.dao;

import com.example.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    List<Account> findAccounts();

    void addAccount(Account account, boolean vipFlag);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

}