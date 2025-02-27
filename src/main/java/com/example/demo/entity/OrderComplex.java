package com.example.demo.entity;

import java.util.List;

public class OrderComplex {

    String dingdanh;
    String dingdanm;

    List<Order> orderList;

    public OrderComplex() {
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "OrderComplex{" +
                "dingdanh='" + dingdanh + '\'' +
                ", dingdanm='" + dingdanm + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
