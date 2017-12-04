package com.berko.crypto.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeUtilsTest {

    @Test
    public void testStringToLong() {
        System.out.println(TimeUtils.stringToLong("2017-12-04"));
        assertEquals(1512345600L, TimeUtils.stringToLong("2017-12-04"));
    }
}
