package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductMaterial;
import com.example.demo.repository.ProductMaterialRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMaterialService {

    @Autowired
    private ProductMaterialRepository productRepository;

    public List<ProductMaterial> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductMaterial> queryProductsByChanpdma(String chanpdma) {
        return productRepository.findByChanpdma(chanpdma);
    }

    public List<ProductMaterial> queryProductsByChanpdmaWuliaobh(ProductMaterial productMaterial) {
        return productRepository.findByChanpdmaWuliaobh(productMaterial.getChanpdma(),productMaterial.getWuliaobh());
    }

    public int saveProductMaterial(ProductMaterial productMaterial) {
        System.out.println("start saveProduct");
        return productRepository.insert(productMaterial);
    }

    public int updateProductMaterial(ProductMaterial productMaterial) {
        System.out.println("start updateProductMaterial");
        return productRepository.update(productMaterial);
    }
}