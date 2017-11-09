
package com.berko.crypto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressInfo {

    @SerializedName("hash160")
    @Expose
    private String hash160;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("n_tx")
    @Expose
    private Integer nTx;
    @SerializedName("total_received")
    @Expose
    private Long totalReceived;
    @SerializedName("total_sent")
    @Expose
    private Long totalSent;
    @SerializedName("final_balance")
    @Expose
    private Long finalBalance;
    @SerializedName("txs")
    @Expose
    private List<Tx> txs = null;

    public String getHash160() {
        return hash160;
    }

    public void setHash160(String hash160) {
        this.hash160 = hash160;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNTx() {
        return nTx;
    }

    public void setNTx(Integer nTx) {
        this.nTx = nTx;
    }

    public Long getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(Long totalReceived) {
        this.totalReceived = totalReceived;
    }

    public Long getTotalSent() {
        return totalSent;
    }

    public void setTotalSent(Long totalSent) {
        this.totalSent = totalSent;
    }

    public Long getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Long finalBalance) {
        this.finalBalance = finalBalance;
    }

    public List<Tx> getTxs() {
        return txs;
    }

    public void setTxs(List<Tx> txs) {
        this.txs = txs;
    }

}
