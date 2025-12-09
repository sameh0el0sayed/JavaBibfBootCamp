package com.Account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AccountModel {

    private final String accountId;
    private final String userId;         // Link to CustomerModel.id
    private final AccountType accountType;
    private double balance;

    @JsonCreator
    public AccountModel(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("userId") String userId,
            @JsonProperty("accountType") AccountType accountType,
            @JsonProperty("balance") double balance) {

        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters
    public String getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    // Business logic
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid deposit amount");
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid withdrawal amount");
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance");
        this.balance -= amount;
    }
}
