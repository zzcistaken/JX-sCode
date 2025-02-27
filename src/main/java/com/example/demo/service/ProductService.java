package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public int saveProduct(Product product) {
        System.out.println("start saveProduct");
        return productRepository.insert(product);
    }

    public Product findOneByChanpdma (String chanpdma) {
        List<Product> list = productRepository.findAllByChanpdma(chanpdma);
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}