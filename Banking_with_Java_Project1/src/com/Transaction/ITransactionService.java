package com.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ITransactionService {
    ArrayList<TransactionModel> findAll();

    List<TransactionModel> findByAccountId(String accountId);

    List<TransactionModel> findFilterByAccountId(String accountId, LocalDateTime fromTime, LocalDateTime toTime);

    void addDepositLog(String accountId, double amount);

    void addWithdrawLog(String accountId, double amount);

    void addTransferLog(String fromAccountId, String toAccountId, double amount);
}
