package com.example.demo.controller;

import com.example.demo.dto.MaterialRes;
import com.example.demo.dto.Result;
import com.example.demo.entity.Material;
import com.example.demo.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-m")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @PostMapping(value = "/addMaterial")
    public Result createMaterial(@RequestBody Material material) {
        System.out.println("start createMaterial:" + material.toString());
        materialService.saveMaterial(material);

        return Result.ok(material);
    }

    @GetMapping("/materials")
    public Result getMaterials(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        System.out.println("page: " + page);
        System.out.println("page: " + limit);

        List<Material> materialList = materialService.getAllMaterials();
        List<Material> materialListLimit = new ArrayList<>();
        int start = (page - 1) * limit;
        int count = 0;
        for(int i=0; i<materialList.size();i++) {
            //start
            if(i >= start) {
                count++;
                materialListLimit.add(materialList.get(i));
            }
            if(count >= limit) {
                break;
            }

        }
        MaterialRes materialRes = new MaterialRes();
        materialRes.setCount(materialList.size());
        materialRes.setMaterialList(materialListLimit);
        System.out.println(materialRes.toString());
        return Result.ok(materialRes);
    }

}