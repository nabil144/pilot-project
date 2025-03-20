package com.luv2code.aopdemo.DAO;

import com.luv2code.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account theAccount,boolean vipFlag);
    boolean doWork();

}
