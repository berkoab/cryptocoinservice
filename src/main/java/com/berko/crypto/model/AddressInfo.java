package com.berko.crypto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AddressInfo {
    public static enum Direction{INCOMING, OUTGOING}

    private String address;
    private Set<String> fromAddresses = new HashSet<String>();
    private Set<String> toAddresses = new HashSet<String>();
    private Direction direction = null;
    private double transactionAmount;
    private double historicalTransactAmount;
    private long time;
    private String date;
    private String transactionId;

    public double getHistoricalTransactAmount() {
        return historicalTransactAmount;
    }

    public void setHistoricalTransactAmount(double historicalTransactAmount) {
        this.historicalTransactAmount = historicalTransactAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Set<String> getFromAddresses() {
        return fromAddresses;
    }

    public void setFromAddresses(Set<String> fromAddresses) {
        this.fromAddresses = fromAddresses;
    }

    public Set<String> getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(Set<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
