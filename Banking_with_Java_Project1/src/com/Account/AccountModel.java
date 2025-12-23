package com.Account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AccountModel {

    private final String accountId;
    private final String userId;         // Link to CustomerModel.id
    private final AccountType accountType;
    private double balance;
    private String cardType;
    private int overdraftCount = 0; // number of overdrafts
    private boolean active = true;

    @JsonCreator
    public AccountModel(
            @JsonProperty("accountId") String accountId,
            @JsonProperty("userId") String userId,
            @JsonProperty("accountType") AccountType accountType,
            @JsonProperty("balance") double balance ,
            @JsonProperty("cardType") String cardType,
            @JsonProperty("overdraftCount") int overdraftCount,
            @JsonProperty("active") boolean active) {

        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
        this.cardType = cardType;
        this.overdraftCount = overdraftCount;
        this.active = active;
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

public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getOverdraftCount() {
        return overdraftCount;
    }

    public void setOverdraftCount(int overdraftCount) {
        this.overdraftCount = overdraftCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
