package com.example.demo.dto;

import com.example.demo.entity.Material;

import java.util.List;

public class MaterialRes {

    private int count;
    private List<Material> materialList;

    public MaterialRes() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    @Override
    public String toString() {
        return "MaterialRes{" +
                "count=" + count +
                ", materialList=" + materialList +
                '}';
    }
}
