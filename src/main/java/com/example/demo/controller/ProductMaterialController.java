package com.example.demo.controller;

import com.example.demo.dto.ProductMaterialQueryRes;
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
public class ProductMaterialController {

    @Autowired
    private ProductMaterialService productMaterialService;
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getAllProductMaterial")
    public Result getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        System.out.println("page: " + page);
        System.out.println("page: " + limit);

        List<ProductMaterial> productList = productMaterialService.getAllProducts();
        List<ProductMaterial> productListLimit = new ArrayList<>();
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
        productRes.setProductMaterialList(productListLimit);
        System.out.println(productRes.toString());
        return Result.ok(productRes);
    }

    @PostMapping(value = "/addProductMaterial")
    public Result createProduct(@RequestBody ProductMaterial productMaterial) {
        System.out.println("start createProduct:" + productMaterial.toString());
        productMaterialService.saveProductMaterial(productMaterial);

        return Result.ok();
    }

    @PostMapping(value = "/queryProductMaterial")
    public Result queryProductMaterial(@RequestBody ProductMaterial product) {
        System.out.println("start queryProductMaterial:" + product.toString());
        Product p = productService.findOneByChanpdma(product.getChanpdma());
        List<ProductMaterial> list = productMaterialService.queryProductsByChanpdma(product.getChanpdma());
        ProductMaterialQueryRes result = new ProductMaterialQueryRes();
        result.setChanpdma(product.getChanpdma());
        result.setChanpmch(p.getChanpmch());
        result.setRes(list);
        result.setTotal(list.size());
        System.out.println("end queryProductMaterial:" + result.toString());
        return Result.ok(result);
    }

}