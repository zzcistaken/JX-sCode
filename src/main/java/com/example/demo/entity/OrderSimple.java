package com.example.demo.entity;

public class OrderSimple {

    private int id;
    private String dingdanh;
    private String dingdanm;
    private String beizhuxx;

    public OrderSimple() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getBeizhuxx() {
        return beizhuxx;
    }

    public void setBeizhuxx(String beizhuxx) {
        this.beizhuxx = beizhuxx;
    }

    @Override
    public String toString() {
        return "orderSimple{" +
                "id=" + id +
                ", dingdanh='" + dingdanh + '\'' +
                ", dingdanm='" + dingdanm + '\'' +
                ", beizhuxx='" + beizhuxx + '\'' +
                '}';
    }
}
