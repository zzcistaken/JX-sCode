package com.example.demo.controller;

import com.example.demo.dto.ProductRes;
import com.example.demo.dto.Result;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductMaterial;
import com.example.demo.service.ProductMaterialService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-p")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMaterialService productMaterialService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/addProduct")
    public Result createProduct(@RequestBody Product product) {
        System.out.println("start createProduct:" + product.toString());

        Product product1 = productService.findOneByChanpdma(product.getChanpdma());

        if(product1 == null ) {
            System.out.println("product1: new");
            product1 = new Product();
            product1.setChanpdma(product.getChanpdma());
            product1.setChanpmch(product.getChanpmch());
            productService.saveProduct(product1);
            for(ProductMaterial productMaterial: product.getMaterialList()) {
                productMaterial.setChanpdma(product.getChanpdma());
                productMaterial.setChanpmch(product.getChanpmch());
                productMaterialService.saveProductMaterial(productMaterial);
            }
        } else {
            System.out.println("product1:" + product1);
            for(ProductMaterial productMaterial: product.getMaterialList()) {
                List<ProductMaterial> list =productMaterialService.queryProductsByChanpdmaWuliaobh(productMaterial);
                productMaterial.setChanpdma(product.getChanpdma());
                productMaterial.setChanpmch(product.getChanpmch());
                if(list.size() == 0) {
                    productMaterialService.saveProductMaterial(productMaterial);
                } else {
                    productMaterial.setId(list.get(0).getId());
                    productMaterialService.saveProductMaterial(productMaterial);
                }
            }
        }

//        productService.saveProduct(product);

        return Result.ok(product);
    }

    @GetMapping("/products")
    public Result getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        System.out.println("page: " + page);
        System.out.println("page: " + limit);

        List<Product> productList = productService.getAllProducts();
        List<Product> productListLimit = new ArrayList<>();
        int start = (page - 1) * limit;
        int count = 0;
        for(int i=0; i<productList.size();i++) {
            //start
            if(i >= start) {
                count++;
                productListLimit.add(productList.get(i));
            }
            if(count >= limit) {
                break;
            }

        }
        ProductRes productRes = new ProductRes();
        productRes.setCount(productList.size());
        productRes.setProductList(productListLimit);
        System.out.println(productRes.toString());
        return Result.ok(productRes);
    }

}