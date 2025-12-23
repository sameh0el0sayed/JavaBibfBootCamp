package com;

import com.Transaction.TransactionModel;
import com.Transaction.TransactionService;
import com.Transaction.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionService service;
    private FileHelper<TransactionModel> fileHelperMock;

    @BeforeEach
    public void setup() {
        // Mock FileHelper
        fileHelperMock = mock(FileHelper.class);

        // Empty transaction list
        ArrayList<TransactionModel> txList = new ArrayList<>();
        when(fileHelperMock.readAll()).thenReturn(txList);

        // Inject mocks via subclass
        service = new TransactionService() {
            {

                this.findAll().addAll(txList);
                this.fileHelper = fileHelperMock;
            }
        };
    }

    @Test
    public void testAddDepositLog() {
        service.addDepositLog("A1", 100);

        List<TransactionModel> txs = service.findByAccountId("A1");
        assertEquals(1, txs.size());
        assertEquals(100, txs.get(0).getAmount());
        assertEquals(TransactionType.DEPOSIT, txs.get(0).getType());

        verify(fileHelperMock, atLeastOnce()).saveAll(any());
    }

    @Test
    public void testAddWithdrawLog() {
        service.addWithdrawLog("A1", 50);

        List<TransactionModel> txs = service.findByAccountId("A1");
        assertEquals(1, txs.size());
        assertEquals(-50, txs.get(0).getAmount());
        assertEquals(TransactionType.WITHDRAW, txs.get(0).getType());

        verify(fileHelperMock, atLeastOnce()).saveAll(any());
    }

    @Test
    public void testFindByAccountId() {
        service.save(new TransactionModel("T1", TransactionType.DEPOSIT, "A1", null, null, 100, "2025-12-08T10:00:00.000000"));
        service.save(new TransactionModel("T2", TransactionType.DEPOSIT, "A2", null, null, 200, "2025-12-08T11:00:00.000000"));

        List<TransactionModel> result = service.findByAccountId("A1");
        assertEquals(1, result.size());
        assertEquals("T1", result.get(0).getTransactionId());
    }

    @Test
    public void testFindFilterByAccountIdWithValidAndInvalidDates() {
        // Valid timestamp
        service.save(new TransactionModel("T1", TransactionType.DEPOSIT, "A1", null, null, 100, "2025-12-08T10:00:00.000000"));
        // Invalid timestamp
        service.save(new TransactionModel("T2", TransactionType.DEPOSIT, "A1", null, null, 50, "INVALID"));

        LocalDateTime from = LocalDateTime.parse("2025-12-08T00:00:00.000000", TransactionService.FORMATTER);
        LocalDateTime to = LocalDateTime.parse("2025-12-09T00:00:00.000000", TransactionService.FORMATTER);

        List<TransactionModel> result = service.findFilterByAccountId("A1", from, to);

        // Should skip invalid timestamp and include only T1
        assertEquals(1, result.size());
        assertEquals("T1", result.get(0).getTransactionId());
    }
}
