package com.berko.crypto.model;

import java.util.ArrayList;

public class SingleTransaction {
    public static enum Direction{INCOMING, OUTGOING}

    private String address;
    private ArrayList<String> outAddresses;
    private Direction direction = null;
    private double totalInputs;
    private double totalOutputs;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getOutAddresses() {
        return outAddresses;
    }

    public void setOutAddresses(ArrayList<String> outAddresses) {
        this.outAddresses = outAddresses;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getTotalInputs() {
        return totalInputs;
    }

    public void setTotalInputs(double totalInputs) {
        this.totalInputs = totalInputs;
    }

    public double getTotalOutputs() {
        return totalOutputs;
    }

    public void setTotalOutputs(double totalOutputs) {
        this.totalOutputs = totalOutputs;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    private double fees;
    private long transactionAmount;

}
