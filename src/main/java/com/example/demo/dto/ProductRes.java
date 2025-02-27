package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductMaterial;

import java.util.List;

public class ProductRes {

    private int count;
    private List<Product> productList;
    private List<ProductMaterial> productMaterialList;

    public ProductRes() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ProductMaterial> getProductMaterialList() {
        return productMaterialList;
    }

    public void setProductMaterialList(List<ProductMaterial> productMaterialList) {
        this.productMaterialList = productMaterialList;
    }

    @Override
    public String toString() {
        return "ProductRes{" +
                "count=" + count +
                ", productList=" + productList +
                ", productMaterialList=" + productMaterialList +
                '}';
    }
}
