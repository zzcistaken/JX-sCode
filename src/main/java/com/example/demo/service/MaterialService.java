package com.example.demo.service;

import com.example.demo.entity.Material;
import com.example.demo.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public int saveMaterial(Material material) {
        System.out.println("start saveMaterial");
        return materialRepository.insert(material);
    }

    public Material findOneByWuliaobh (int wuliaobh) {
        List<Material> list = materialRepository.findAllByWuliaobh(wuliaobh);
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        return new Material();
    }

}