package com.example.demo.entity;

public class PartRes {

    private String dingdanh;
    private int wuliaobh;
    private int wuliaocd;
    private int index;
    private String coefficient;
    private int num;


    public PartRes() {
    }

    public String getDingdanh() {
        return dingdanh;
    }

    public void setDingdanh(String dingdanh) {
        this.dingdanh = dingdanh;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PartRes{" +
                "index=" + index +
                ", coefficient='" + coefficient + '\'' +
                ", num=" + num +
                '}';
    }
}
