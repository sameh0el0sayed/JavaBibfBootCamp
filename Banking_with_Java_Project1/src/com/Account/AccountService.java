package com.Account;

import com.FileHelper;
import com.Transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import static org.mockito.Mockito.*;

public class AccountService implements IAccountService{

    protected FileHelper<AccountModel> fileHelper;
    protected ArrayList<AccountModel> accounts;
    private  String Userid;

    protected TransactionService transactionService;
    public AccountService(String _userID) {
        this.fileHelper = new FileHelper<>(
                "src/com/data/accounts.json",
                AccountModel[].class
        );
        this.accounts = new ArrayList<>();
        this.Userid=_userID;
        this.transactionService=new TransactionService();
        loadAll();
    }

    public void loadAll() {
        this.accounts = fileHelper.readAll();
    }

    public void saveAll() {
        fileHelper.saveAll(accounts);
    }

    public void create(AccountModel account) {
        accounts.add(account);
        saveAll();
    }

    public AccountModel findById(String accountId) {
        return accounts.stream()
                .filter(a -> a.getAccountId().equals(accountId))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<AccountModel> findByUserId() {
        ArrayList<AccountModel> result = new ArrayList<>();
        for (AccountModel a : accounts) {
            if (a.getUserId().equals(Userid)) {
                result.add(a);
            }
        }
        return result;
    }


    public AccountModel findSavingsAccount() {
        return accounts.stream()
                .filter(a -> a.getUserId().equals(Userid)
                        && a.getAccountType() == AccountType.SAVINGS)
                .findFirst().orElse(null);
    }

    public AccountModel findCheckingAccount() {
        return accounts.stream()
                .filter(a -> a.getUserId().equals(Userid)
                        && a.getAccountType() == AccountType.CHECKING)
                .findFirst().orElse(null);
    }

    public void deposit(String accountId, double amount) {
        AccountModel acc = findById(accountId);
        if (acc == null)
            throw new IllegalArgumentException("Account not found");

        acc.deposit(amount);
        saveAll();

        transactionService.addDepositLog(
                accountId,
                amount
        );
    }

    public void withdraw(String accountId, double amount) {
        AccountModel acc = findById(accountId);
        if (acc == null)
            throw new IllegalArgumentException("Account not found");

        acc.withdraw(amount);
        saveAll();

        transactionService.addWithdrawLog(
                accountId,
                amount
        );
    }
    public void AmountTransfer(String FromAccountId,String ToAccountId, double amount) {
        AccountModel FromAcc = findById(FromAccountId);
        AccountModel ToAcc = findById(ToAccountId);
        if (FromAcc == null)
            throw new IllegalArgumentException("From Account not found");
        if (ToAcc == null)
            throw new IllegalArgumentException("To Account not found");
        FromAcc.withdraw(amount);
        ToAcc.deposit(amount);
        saveAll();

        transactionService.addTransferLog(
                FromAccountId, ToAccountId,
                amount
        );
    }



}
