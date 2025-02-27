package com.example.demo.entity;

public class Material {

    private Long id;

    private String wuliaolx;
    private int wuliaobh;
    private int wuliaocd;
    private String beizhuxx;

    public Material() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBeizhuxx() {
        return beizhuxx;
    }

    public void setBeizhuxx(String beizhuxx) {
        this.beizhuxx = beizhuxx;
    }

    public String getWuliaolx() {
        return wuliaolx;
    }

    public void setWuliaolx(String wuliaolx) {
        this.wuliaolx = wuliaolx;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", wuliaolx='" + wuliaolx + '\'' +
                ", wuliaobh=" + wuliaobh +
                ", wuliaocd=" + wuliaocd +
                ", beizhuxx='" + beizhuxx + '\'' +
                '}';
    }
}
