package com.example.demo.entity;

public class ProductBaseInfo {

    private String chanpdma;
    private String chanpmch;
    private String beizhuxx;

    public ProductBaseInfo() {
    }

    public String getChanpdma() {
        return chanpdma;
    }

    public void setChanpdma(String chanpdma) {
        this.chanpdma = chanpdma;
    }

    public String getChanpmch() {
        return chanpmch;
    }

    public void setChanpmch(String chanpmch) {
        this.chanpmch = chanpmch;
    }

    public String getBeizhuxx() {
        return beizhuxx;
    }

    public void setBeizhuxx(String beizhuxx) {
        this.beizhuxx = beizhuxx;
    }

    @Override
    public String toString() {
        return "ProductBaseInfo{" +
                "chanpdma='" + chanpdma + '\'' +
                ", chanpmch='" + chanpmch + '\'' +
                ", beizhuxx='" + beizhuxx + '\'' +
                '}';
    }
}
