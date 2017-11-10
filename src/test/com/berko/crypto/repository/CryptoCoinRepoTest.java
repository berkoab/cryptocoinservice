package com.berko.crypto.repository;

import com.berko.crypto.model.SingleTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoCoinRepoTest {

    @Test
    public void getInfo() {
        CryptoCoinRepo repo = new CryptoCoinRepo();
        System.out.println(repo.getInfo("1AJbsFZ64EpEfS5UAjAfcUG8pH8Jn3rn1F").get(1).getTransactionAmount());
        assertEquals(46.0, repo.getInfo("1AJbsFZ64EpEfS5UAjAfcUG8pH8Jn3rn1F").get(0).getTransactionAmount());
        assertEquals(SingleTransaction.Direction.OUTGOING, repo.getInfo("1AJbsFZ64EpEfS5UAjAfcUG8pH8Jn3rn1F").get(0).getDirection());
//        assertEquals(0, balance);

    }

}