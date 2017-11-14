package com.berko.crypto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class SingleTransaction {
    public static enum Direction{INCOMING, OUTGOING}

    private String address;
    private ArrayList<String> fromAddresses = new ArrayList<String>();
    private ArrayList<String> toAddresses = new ArrayList<String>();
    private Direction direction = null;
    private double transactionAmount;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<String> getFromAddresses() {
        return fromAddresses;
    }

    public void setFromAddresses(ArrayList<String> fromAddresses) {
        this.fromAddresses = fromAddresses;
    }

    public ArrayList<String> getToAddresses() {
        return toAddresses;
    }

    public void setToAddresses(ArrayList<String> toAddresses) {
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

}
