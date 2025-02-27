package com.example.demo.dto;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderMaterial;
import com.example.demo.entity.PartRes;

import java.util.List;

public class OrderRes {

    private String dingdanm;
    private int count;
    private List<Order> orderList;
    private List<OrderMaterial> prderMaterialList;
    private int totalPart;
    private List<PartRes> partResList;

    public OrderRes() {
    }

    public String getDingdanm() {
        return dingdanm;
    }

    public void setDingdanm(String dingdanm) {
        this.dingdanm = dingdanm;
    }

    public int getTotalPart() {
        return totalPart;
    }

    public void setTotalPart(int totalPart) {
        this.totalPart = totalPart;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> prderList) {
        this.orderList = prderList;
    }

    public List<OrderMaterial> getOrderMaterialList() {
        return prderMaterialList;
    }

    public void setOrderMaterialList(List<OrderMaterial> prderMaterialList) {
        this.prderMaterialList = prderMaterialList;
    }



    public List<OrderMaterial> getPrderMaterialList() {
        return prderMaterialList;
    }

    public void setPrderMaterialList(List<OrderMaterial> prderMaterialList) {
        this.prderMaterialList = prderMaterialList;
    }

    public List<PartRes> getPartResList() {
        return partResList;
    }

    public void setPartResList(List<PartRes> partResList) {
        this.partResList = partResList;
    }

    @Override
    public String toString() {
        return "OrderRes{" +
                "count=" + count +
                ", prderList=" + orderList +
                ", prderMaterialList=" + prderMaterialList +
                ", partResList=" + partResList +
                '}';
    }
}
