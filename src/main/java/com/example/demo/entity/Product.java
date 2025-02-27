package com.example.demo.entity;

import java.util.List;

public class Product {

    private Long id;

    String chanpdma;
    String chanpmch;

    List<ProductMaterial> materialList;

    public Product() {

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

    public List<ProductMaterial> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<ProductMaterial> materialList) {
        this.materialList = materialList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", chanpdma='" + chanpdma + '\'' +
                ", chanpmch='" + chanpmch + '\'' +
                ", materialList=" + materialList +
                '}';
    }
}
