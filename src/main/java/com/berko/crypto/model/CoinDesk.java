
package com.berko.crypto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class CoinDesk {

    @SerializedName("bpi")
    @Expose
    private Map<String, Double> bpi = new HashMap<String, Double>();

    @SerializedName("disclaimer")
    @Expose
    private String disclaimer;
    @SerializedName("time")
    @Expose
    private Time time;

    public Map<String, Double> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Double> bpi) {
        this.bpi = bpi;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
