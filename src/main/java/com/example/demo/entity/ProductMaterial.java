package com.example.demo.entity;

public class ProductMaterial {

    private Long id;

    private String chanpdma;
    private String chanpmch;

    private int wuliaobh;
    private int wuliaocd;
    private int wuliaosl;
    private String beizhuxx;

    public ProductMaterial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getWuliaobh() {
        return wuliaobh;
    }

    public void setWuliaobh(int wuliaobh) {
        this.wuliaobh = wuliaobh;
    }

    public int getWuliaocd() {
        return wuliaocd;
    }

    public void setWuliaocd(int wuliaocd) {
        this.wuliaocd = wuliaocd;
    }

    public int getWuliaosl() {
        return wuliaosl;
    }

    public void setWuliaosl(int wuliaosl) {
        this.wuliaosl = wuliaosl;
    }

    public String getBeizhuxx() {
        return beizhuxx;
    }

    public void setBeizhuxx(String beizhuxx) {
        this.beizhuxx = beizhuxx;
    }

    @Override
    public String toString() {
        return "ProductMaterial{" +
                "chanpdma='" + chanpdma + '\'' +
                ", chanpmch='" + chanpmch + '\'' +
                ", wuliaobh=" + wuliaobh +
                ", wuliaocd=" + wuliaocd +
                ", wuliaosl=" + wuliaosl +
                ", beizhuxx='" + beizhuxx + '\'' +
                '}';
    }
}
