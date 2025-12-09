package com.Transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionModel {

    private final String transactionId;
    private final TransactionType type;

    private final String accountId;        // For DEPOSIT / WITHDRAW
    private final String fromAccountId;    // For TRANSFER
    private final String toAccountId;      // For TRANSFER

    private final double amount;
    private final String timestamp;

    @JsonCreator
    public TransactionModel(
            @JsonProperty("transactionId") String transactionId,
            @JsonProperty("type") TransactionType type,
            @JsonProperty("accountId") String accountId,
            @JsonProperty("fromAccountId") String fromAccountId,
            @JsonProperty("toAccountId") String toAccountId,
            @JsonProperty("amount") double amount,
            @JsonProperty("timestamp") String timestamp
    ) {
        this.transactionId = transactionId;
        this.type = type;
        this.accountId = accountId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() { return transactionId; }
    public TransactionType getType() { return type; }
    public String getAccountId() { return accountId; }
    public String getFromAccountId() { return fromAccountId; }
    public String getToAccountId() { return toAccountId; }
    public double getAmount() { return amount; }
    public String getTimestamp() { return timestamp; }
}
