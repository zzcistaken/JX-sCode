package com.example.demo.entity;

import java.util.List;

public class Order {

    private Long id;

    String chanpdma;
    String chanpmch;
    String dingdanh;
    String dingdanm;

    private int chanpshl;

    public Order() {

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

    public int getChanpshl() {
        return chanpshl;
    }

    public void setChanpshl(int chanpshl) {
        this.chanpshl = chanpshl;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", chanpdma='" + chanpdma + '\'' +
                ", chanpmch='" + chanpmch + '\'' +
                ", dingdanh='" + dingdanh + '\'' +
                ", dingdanm='" + dingdanm + '\'' +
                ", chanpshl=" + chanpshl +
                '}';
    }
}
