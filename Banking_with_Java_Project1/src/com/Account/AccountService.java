package com.Account;

import com.FileHelper;
import com.Transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static org.mockito.Mockito.*;

public class AccountService implements IAccountService{

    protected FileHelper<AccountModel> fileHelper;
    protected ArrayList<AccountModel> accounts;
    private  String Userid;
    private DebitCardService debitCardService;
    protected TransactionService transactionService;
    public AccountService(String _userID) {
        this.fileHelper = new FileHelper<>(
                "src/com/data/accounts.json",
                AccountModel[].class
        );
        this.accounts = new ArrayList<>();
        this.Userid=_userID;
        this.transactionService=new TransactionService();
        this.debitCardService=new DebitCardService();
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
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
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

    public ArrayList<AccountModel> findByUserId(String _userId) {
        ArrayList<AccountModel> result = new ArrayList<>();
        for (AccountModel a : accounts) {
            if (a.getUserId().equals(_userId)) {
                result.add(a);
            }
        }
        return result;
    }


    public AccountModel findSavingsAccount() {
        return accounts.stream()
                .filter(a -> a.getUserId().equals(Userid)
                        && a.getAccountType() == AccountType.SAVINGS)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Savings account not found for user: " + Userid));

    }

    public AccountModel findCheckingAccount() {
        return accounts.stream()
                .filter(a -> a.getUserId().equals(Userid)
                        && a.getAccountType() == AccountType.CHECKING)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Savings account not found for user: " + Userid));
    }

    public void deposit(String accountId, double amount) {
        AccountModel acc = findById(accountId);
        double depositCardLimitation=debitCardService.getCardByType(acc.getCardType()).getDepositLimitPerDay();
        if (amount>depositCardLimitation)
            throw new IllegalArgumentException("Deposit Card Limitation");
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
        double withdrawCardLimitation=debitCardService.getCardByType(acc.getCardType()).getWithdrawLimitPerDay();
        if (amount>withdrawCardLimitation)
            throw new IllegalArgumentException("Withdraw Card Limitation");
        if (acc == null)
            throw new IllegalArgumentException("Account not found");
        if (!acc.isActive()) {
            throw new RuntimeException("Account is deactivated due to overdrafts.");
        }
        double balance = acc.getBalance();

        if (balance - amount < 0) {
            acc.setBalance(balance - amount - 35); // apply $35 overdraft fee
            acc.setOverdraftCount(acc.getOverdraftCount() + 1);

            System.out.println(RED + "Overdraft! $35 fee applied.");

            // 3️⃣ Deactivate if more than 2 overdrafts
            if (acc.getOverdraftCount() >= 2) {
                acc.setActive(false);
                System.out.println(RED + "Account deactivated due to multiple overdrafts.");
            }

        } else {
            // normal withdrawal
            acc.setBalance(balance - amount);
        }
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

        double AmountTransferCardLimitation=debitCardService.getCardByType(FromAcc.getCardType()).getTransferLimitPerDay();
        if (amount>AmountTransferCardLimitation)
            throw new IllegalArgumentException("Amount Transfer Card Limitation");

        if (FromAcc == null)
            throw new IllegalArgumentException("From Account not found");
        else if (ToAcc == null)
            throw new IllegalArgumentException("To Account not found");
        FromAcc.withdraw(amount);
        ToAcc.deposit(amount);
        saveAll();

        transactionService.addTransferLog(
                FromAccountId, ToAccountId,
                amount
        );
    }

    public void reactivateAccount(String accountId, double payment) {
        AccountModel account = findById(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found.");
        }

        if (account.isActive()) {
            System.out.println(GREEN + "Account is already active.");
            return;
        }

        account.setBalance(account.getBalance() + payment); // payment reduces negative balance
        if (account.getBalance() >= 0) {
            account.setActive(true);
            account.setOverdraftCount(0); // reset overdraft counter
            System.out.println(GREEN + "Account reactivated successfully!");
        } else {
            System.out.println(RED + "Payment insufficient. Account still negative.");
        }

        saveAll();
    }


}
