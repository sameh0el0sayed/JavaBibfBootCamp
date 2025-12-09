package com.Account;

import java.util.ArrayList;

public interface  IAccountService {
    // Load and save
    void loadAll();
    void saveAll();

    // Create and find accounts
    void create(AccountModel account);
    AccountModel findById(String accountId);
    ArrayList<AccountModel> findByUserId();
    AccountModel findSavingsAccount();
    AccountModel findCheckingAccount();

    // Transactions
    void deposit(String accountId, double amount);
    void withdraw(String accountId, double amount);
 }
