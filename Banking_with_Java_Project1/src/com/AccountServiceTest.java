package com;


import com.Account.AccountModel;
import com.Account.AccountService;
import com.Account.AccountType;
import com.Transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    private AccountService service;
    private FileHelper<AccountModel> fileHelperMock;
    private TransactionService transactionMock;

    @BeforeEach
    void setup() {
        fileHelperMock = mock(FileHelper.class);
        transactionMock = mock(TransactionService.class);

        // Mock reading JSON → empty list
        when(fileHelperMock.readAll()).thenReturn(new ArrayList<>());

        // Inject mocks via subclass
        service = new AccountService("U1") {
            {
                this.accounts = new ArrayList<>();
                this.fileHelper = fileHelperMock;
                this.transactionService = transactionMock;
            }
        };

        // Create sample accounts
        service.create(new AccountModel("A1", "U1", AccountType.SAVINGS, 500,"Mastercard Titanium",0,true));
        service.create(new AccountModel("A2", "U1", AccountType.CHECKING, 300,"Mastercard",0,false));
    }

    @Test
    void testFindSavingsAccount() {
        AccountModel savings = service.findSavingsAccount();
        assertNotNull(savings);
        assertEquals(AccountType.SAVINGS, savings.getAccountType());
        assertEquals("A1", savings.getAccountId());
    }

    @Test
    void testFindCheckingAccount() {
        AccountModel checking = service.findCheckingAccount();
        assertNotNull(checking);
        assertEquals(AccountType.CHECKING, checking.getAccountType());
        assertEquals("A2", checking.getAccountId());
    }

    @Test
    void testDeposit() {
        service.deposit("A1", 200);

        AccountModel acc = service.findById("A1");
        assertEquals(700, acc.getBalance());

        verify(transactionMock, times(1)).addDepositLog("A1", 200);
        verify(fileHelperMock, atLeastOnce()).saveAll(any());
    }

    @Test
    void testDepositInvalidAccount() {
        assertThrows(IllegalArgumentException.class,
                () -> service.deposit("INVALID", 100));
    }

    @Test
    void testWithdraw() {
        service.withdraw("A2", 100);

        AccountModel acc = service.findById("A2");
        assertEquals(200, acc.getBalance());

        verify(transactionMock, times(1)).addWithdrawLog("A2", 100);
        verify(fileHelperMock, atLeastOnce()).saveAll(any());
    }

    @Test
    void testWithdrawInvalidAccount() {
        assertThrows(IllegalArgumentException.class,
                () -> service.withdraw("INVALID", 50));
    }

    @Test
    void testWithdrawInsufficientBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> service.withdraw("A2", 500));
    }
}
