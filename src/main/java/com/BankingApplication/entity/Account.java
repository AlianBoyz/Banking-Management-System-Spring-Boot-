package com.BankingApplication.entity;

import jakarta.persistence.*;

@Entity
@Table (name ="accounts")
public class Account
{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(int id, String accountHolderName, double balance)
    {
        super();
        this.id=id;
        this.accountHolderName=accountHolderName;
        this.balance=balance;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "account_holder_name")
    private String accountHolderName;
    private double balance;

}
