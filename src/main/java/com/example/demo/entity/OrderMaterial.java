package com.example.demo.entity;

public class OrderMaterial {

    private Long id;

    private String dingdanh;
    private String dingdanm;

    private int wuliaobh;
    private int wuliaocd;
    private int wuliaosl;
    private String beizhuxx;

    public OrderMaterial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDingdanh() {
        return dingdanh;
    }

    public void setDingdanh(String dingdanh) {
        this.dingdanh = dingdanh;
    }

    public String getDingdanm() {
        return dingdanm;
    }

    public void setDingdanm(String dingdanm) {
        this.dingdanm = dingdanm;
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
        return "OrderMaterial{" +
                "id=" + id +
                ", dingdanh='" + dingdanh + '\'' +
                ", dingdanm='" + dingdanm + '\'' +
                ", wuliaobh=" + wuliaobh +
                ", wuliaocd=" + wuliaocd +
                ", wuliaosl=" + wuliaosl +
                ", beizhuxx='" + beizhuxx + '\'' +
                '}';
    }
}
