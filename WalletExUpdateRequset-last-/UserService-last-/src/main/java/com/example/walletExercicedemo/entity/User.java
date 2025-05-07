package com.example.walletExercicedemo.entity;
import com.example.walletExercicedemo.entity.User;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "users")

public class User {

    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "email")
    private String email;


    @Column(name = "wallet_balance")
    private BigDecimal walletBalance;


    // constructors
    public User (){

    }

    public User(Long id, String name, String email, BigDecimal walletBalance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.walletBalance = walletBalance;
    }


    // getter/setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(BigDecimal walletBalance) {
        this.walletBalance = walletBalance;
    }

    // toString

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", walletBalance=" + walletBalance +
                '}';
    }


}

