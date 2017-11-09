package com.berko.crypto.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoCoinRepoTest {

    @Test
    public void getInfo() {
        CryptoCoinRepo repo = new CryptoCoinRepo();
        int balance = repo.getInfo("1Ndj6YBUYr8xMttHTRcG3Mt5cuLWawHaNj").getFinalBalance();
        assertEquals(0, balance);

    }

}