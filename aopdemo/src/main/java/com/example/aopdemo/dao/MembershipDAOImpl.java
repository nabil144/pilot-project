package com.example.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public boolean addMember() {

        System.out.println(getClass() + ": doing db work, adding membership account");
        return true;
    }

    @Override
    public void goToSleep() {

        System.out.println(getClass() + ": going to sleep now...");
    }
}
