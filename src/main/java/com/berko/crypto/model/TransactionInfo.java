package com.berko.crypto.model;

import java.util.HashMap;
import java.util.Map;

public class TransactionInfo {
    private Map<String, Double> outgoingBitcoin = new HashMap<String, Double>();
    private Map<String, Double> incomingBitcoin = new HashMap<String, Double>();
    private Map<String, Double> outgoingCurrency = new HashMap<String, Double>();
    private Map<String, Double> incomingCurrency = new HashMap<String, Double>();

    private long time;
    private String date;
    private String transactionId;

    public Map<String, Double> getOutgoingBitcoin() {
        return outgoingBitcoin;
    }

    public void setOutgoingBitcoin(Map<String, Double> outgoingBitcoin) {
        this.outgoingBitcoin = outgoingBitcoin;
    }

    public Map<String, Double> getIncomingBitcoin() {
        return incomingBitcoin;
    }

    public void setIncomingBitcoin(Map<String, Double> incomingBitcoin) {
        this.incomingBitcoin = incomingBitcoin;
    }

    public Map<String, Double> getOutgoingCurrency() {
        return outgoingCurrency;
    }

    public void setOutgoingCurrency(Map<String, Double> outgoingCurrency) {
        this.outgoingCurrency = outgoingCurrency;
    }

    public Map<String, Double> getIncomingCurrency() {
        return incomingCurrency;
    }

    public void setIncomingCurrency(Map<String, Double> incomingCurrency) {
        this.incomingCurrency = incomingCurrency;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
