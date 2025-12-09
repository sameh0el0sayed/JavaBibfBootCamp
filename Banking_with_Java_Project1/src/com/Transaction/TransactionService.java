package com.Transaction;
import com.FileHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionService implements ITransactionService {

    protected FileHelper<TransactionModel> fileHelper;
    private final ArrayList<TransactionModel> transactions;
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");


    public TransactionService() {
        this.fileHelper = new FileHelper<>("src/com/data/transactions.json", TransactionModel[].class);
        this.transactions = fileHelper.readAll();
    }

    // Save full list back to JSON
    private void saveAll() {
        fileHelper.saveAll(transactions);
    }

    // Add one new transaction
    public void save(TransactionModel tx) {
        transactions.add(tx);
        saveAll();
    }

     public ArrayList<TransactionModel> findAll() {
        return transactions;
    }

    public List<TransactionModel> findByAccountId(String accountId) {
        return transactions
                .stream()
                .filter(t ->
                        accountId.equals(t.getAccountId()) ||
                                accountId.equals(t.getFromAccountId()) ||
                                accountId.equals(t.getToAccountId()))
                .collect(Collectors.toList());
    }
    public List<TransactionModel> findFilterByAccountId(String accountId, LocalDateTime  fromTime,LocalDateTime  toTime) {
        return transactions.stream()
                .filter(t ->
                        accountId.equals(t.getAccountId()) ||
                                accountId.equals(t.getFromAccountId()) ||
                                accountId.equals(t.getToAccountId()))
                .filter(t -> {
                    try {
                        LocalDateTime txTime = LocalDateTime.parse(t.getTimestamp(), FORMATTER);

                        return !txTime.isBefore(fromTime) && !txTime.isAfter(toTime);

                    } catch (Exception e) {
                        System.out.println("Skipping invalid timestamp: " + t.getTimestamp());
                        return false; // do not include invalid timestamps
                    }
                })
                .collect(Collectors.toList());

    }


    public void addDepositLog(String accountId, double amount) {
        TransactionModel tx = new TransactionModel(
                "T" + System.currentTimeMillis(),
                TransactionType.DEPOSIT,
                accountId,
                null,
                null,
                amount,
                LocalDateTime.now().toString()
        );
        save(tx);
    }

    public void addWithdrawLog(String accountId, double amount) {
        TransactionModel tx = new TransactionModel(
                "T" + System.currentTimeMillis(),
                TransactionType.WITHDRAW,
                accountId,
                null,
                null,
                -amount,  // negative for withdraw
                LocalDateTime.now().toString()
        );
        save(tx);
    }

    public void addTransferLog(String from, String to, double amount) {
        TransactionModel tx = new TransactionModel(
                "T" + System.currentTimeMillis(),
                TransactionType.TRANSFER,
                null,
                from,
                to,
                amount,
                LocalDateTime.now().toString()
        );
        save(tx);
    }

}
