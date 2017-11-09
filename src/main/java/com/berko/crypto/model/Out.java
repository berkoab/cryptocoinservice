
package com.berko.crypto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Out {

    @SerializedName("addr_tag_link")
    @Expose
    private String addrTagLink;
    @SerializedName("addr_tag")
    @Expose
    private String addrTag;
    @SerializedName("spent")
    @Expose
    private Boolean spent;
    @SerializedName("tx_index")
    @Expose
    private Integer txIndex;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("value")
    @Expose
    private Long value;
    @SerializedName("n")
    @Expose
    private Integer n;
    @SerializedName("script")
    @Expose
    private String script;

    public String getAddrTagLink() {
        return addrTagLink;
    }

    public void setAddrTagLink(String addrTagLink) {
        this.addrTagLink = addrTagLink;
    }

    public String getAddrTag() {
        return addrTag;
    }

    public void setAddrTag(String addrTag) {
        this.addrTag = addrTag;
    }

    public Boolean getSpent() {
        return spent;
    }

    public void setSpent(Boolean spent) {
        this.spent = spent;
    }

    public Integer getTxIndex() {
        return txIndex;
    }

    public void setTxIndex(Integer txIndex) {
        this.txIndex = txIndex;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

}
