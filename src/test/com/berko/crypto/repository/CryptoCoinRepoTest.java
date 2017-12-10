package com.berko.crypto.repository;

import com.berko.crypto.model.SingleTransaction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CryptoCoinRepoTest {

    @Test
    public void getInfo() {
        CryptoCoinRepo repo = new CryptoCoinRepo();
        List<SingleTransaction> transactions = repo.getTransactionInfo("1AJbsFZ64EpEfS5UAjAfcUG8pH8Jn3rn1F",
                "2012-01-08","2012-06-08", "USD");
        assertEquals(46.783, transactions.get(0).getTransactionAmount());
        assertEquals(SingleTransaction.Direction.OUTGOING, transactions.get(0).getDirection());
        assertEquals(SingleTransaction.Direction.INCOMING, transactions.get(1).getDirection());
        assertEquals(1.0, transactions.get(2).getTransactionAmount());
        assertEquals(263.528639, transactions.get(0).getHistoricalTransactAmount());
    }
}