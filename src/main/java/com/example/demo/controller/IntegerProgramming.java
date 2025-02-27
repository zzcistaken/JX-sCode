package com.example.demo.controller;

import com.example.demo.dto.Result;
import com.example.demo.entity.Demo1;
import com.example.demo.entity.Demo1Res;
import com.example.demo.entity.Part;
import com.example.demo.entity.PartRes;
import com.example.demo.service.SeperateAndIntegerProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class IntegerProgramming {

    @Autowired
    private SeperateAndIntegerProgramService sepIntService;

    @PostMapping(value = "/addDemo1")
    public Result addDemo1(@RequestBody Demo1 demo1) {
        System.out.println("start createProduct:" + demo1.toString());

        int n = Integer.valueOf(demo1.getGroupLength()); //圆材料长度
        List<Part> parts = demo1.getParts(); //各个分段及需求数量
        int[] part = new int[parts.size()]; //各个分段
        int[] constraints = new int[parts.size()]; //需求数量
        for(int i=0;i<parts.size();i++) {
            part[i] = Integer.valueOf(parts.get(i).getSingleLength());
            constraints[i] = Integer.valueOf(parts.get(i).getSingleCount());
        }

        List<List<Integer>> coefficients = new ArrayList<>(); //分割方案
        List<Integer> resVariables = new ArrayList<>();  //每种方案的数量

        Demo1Res demo1Res = sepIntService.run(n, part, constraints, coefficients, resVariables);

        demo1Res.setGroupLength(demo1.getGroupLength());
        demo1Res.setParts(demo1.getParts());

        System.out.println("end createProduct:" + demo1Res.toString());

        return Result.ok(demo1Res);
    }

}
