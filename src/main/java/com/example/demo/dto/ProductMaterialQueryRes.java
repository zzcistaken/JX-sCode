package com.example.demo.dto;

import com.example.demo.entity.ProductMaterial;

import java.util.List;

public class ProductMaterialQueryRes {

    private String chanpdma;
    private String chanpmch;
    private int total;

    private List<ProductMaterial> res;

    public ProductMaterialQueryRes() {
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

    public List<ProductMaterial> getRes() {
        return res;
    }

    public void setRes(List<ProductMaterial> res) {
        this.res = res;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ProductMaterialQueryRes{" +
                "chanpdma='" + chanpdma + '\'' +
                ", chanpmch='" + chanpmch + '\'' +
                ", res=" + res +
                '}';
    }
}
