package com.example.demo.controller;

import com.example.demo.dto.OrderRes;
import com.example.demo.dto.ProductRes;
import com.example.demo.dto.Result;
import com.example.demo.entity.*;
import com.example.demo.service.MaterialService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductMaterialService;
import com.example.demo.service.SeperateAndIntegerProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api-o")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductMaterialService productService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private SeperateAndIntegerProgramService seperateAndIntegerProgramService;

    @PostMapping("/queryOrderProduct")
    public Result getOrdersByDingdanh(@RequestBody Order order) {
        System.out.println(order.toString());
        List<Order> list = orderService.getOrdersByDingdanh(order.getDingdanh());
        OrderRes orderRes = new OrderRes();
        orderRes.setCount(list.size());
        orderRes.setOrderList(list);
        if(list.size() > 0) {
            orderRes.setDingdanm(list.get(0).getDingdanm());
        }
        System.out.println(orderRes.toString());
        return Result.ok(orderRes);
    }

    @PostMapping("/addOrders")
    public Result addOrders(@RequestBody OrderComplex orderComplex) {
        System.out.println("addOrders: " + orderComplex.toString());
        String dingdanh = orderComplex.getDingdanh();
        String dingdanm = orderComplex.getDingdanm();
        List<OrderSimple> simpleList = orderService.getOrderSimpleByDingdanh(dingdanh);
        if(simpleList == null || simpleList.size() == 0) {
            OrderSimple orderSimple = new OrderSimple();
            orderSimple.setDingdanh(dingdanh);
            orderSimple.setDingdanm(dingdanm);
            orderService.saveOrderSimple(orderSimple);
            for(Order order : orderComplex.getOrderList()) {
                String chanpdma = order.getChanpdma();
                String chanpmch = order.getChanpmch();
                int chanpshl = order.getChanpshl();

                Order order1 = orderService.getOrdersByDingdanhChanpdma(dingdanh, chanpdma);
                if(order1 == null) {
                    order1 = new Order();
                    order1.setDingdanh(dingdanh);
                    order1.setDingdanm(dingdanm);
                    order1.setChanpdma(chanpdma);
                    order1.setChanpmch(chanpmch);
                    order1.setChanpshl(chanpshl);
                    orderService.saveOrder(order1);
                } else {
                    order1.setDingdanh(dingdanh);
                    order1.setDingdanm(dingdanm);
                    order1.setChanpdma(chanpdma);
                    order1.setChanpmch(chanpmch);
                    order1.setChanpshl(chanpshl);
                    orderService.updateOrder(order1);
                }
            }
        }

        return Result.ok();
    }

    @GetMapping("/orderProducts")
    public Result getOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        System.out.println("page: " + page);
        System.out.println("page: " + limit);

        List<Order> orderList = orderService.getAllOrders();
        List<Order> orderListLimit = new ArrayList<>();
        int start = (page - 1) * limit;
        int count = 0;
        for(int i=0; i<orderList.size();i++) {
            //start
            if(i >= start) {
                count++;
                orderListLimit.add(orderList.get(i));
            }
            if(count >= limit) {
                break;
            }

        }
        OrderRes orderRes = new OrderRes();
        orderRes.setCount(orderList.size());
        orderRes.setOrderList(orderListLimit);
        System.out.println(orderRes.toString());
        return Result.ok(orderRes);
    }

    @GetMapping(value = "/getAllProductMaterialSimple")
    public Result getAllProductMaterialSimple(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String dingdanh ) {
        System.out.println("page: " + page);
        System.out.println("page: " + limit);
        System.out.println("dingdanh: " + dingdanh);

        //获取订单列表
        List<OrderSimple> orderSimpleList = null;
        if(dingdanh != null && dingdanh.trim().length() > 0) {
            orderSimpleList = orderService.getOrderSimpleByDingdanh(dingdanh);
        } else {
            orderSimpleList = orderService.getAllOrderSimple();
        }
        System.out.println(orderSimpleList);

        List<Map<Integer, OrderMaterial>> resMapList = new ArrayList<>();
        Map<String, Map> outerMap = new HashMap<>(); //key是订单号
        for(OrderSimple orderSimple : orderSimpleList) {
            List<Order> orderList = orderService.getOrdersByDingdanh(orderSimple.getDingdanh());
            Map<Integer, Map> middleMap = new HashMap<>(); //key是物料编号
            outerMap.put(orderSimple.getDingdanh(), middleMap);
            for (Order order : orderList) {
                //根据订单下的产品，获取产品的物料信息，从而获得一个订单下各个物料需要的数量
                //A001 -- 1 -- 长度x
                //A001 -- 1 -- 长度y
                List<ProductMaterial> productList = productService.queryProductsByChanpdma(order.getChanpdma());
                for (ProductMaterial productMaterial : productList) {
                    int wuliaobh = productMaterial.getWuliaobh();
                    productMaterial.getWuliaocd();
                    productMaterial.getWuliaosl();

                    Map<Integer, OrderMaterial> innerMap = new HashMap<>(); //key是物料长度
                    if(middleMap.containsKey(wuliaobh)) {
                        innerMap = middleMap.get(wuliaobh);
                    } else {
                        middleMap.put(wuliaobh, innerMap);
                    }

                    if (innerMap.containsKey(productMaterial.getWuliaocd())) {
                        OrderMaterial orderMaterial = innerMap.get(productMaterial.getWuliaocd());
                        orderMaterial.setWuliaosl(orderMaterial.getWuliaosl() + productMaterial.getWuliaosl() * order.getChanpshl());
                    } else {
                        OrderMaterial orderMaterial = new OrderMaterial();
                        orderMaterial.setWuliaosl(productMaterial.getWuliaosl() * order.getChanpshl());
                        orderMaterial.setDingdanh(order.getDingdanh());
                        orderMaterial.setDingdanm(order.getDingdanm());
                        orderMaterial.setWuliaobh(productMaterial.getWuliaobh());
                        orderMaterial.setWuliaocd(productMaterial.getWuliaocd());
                        innerMap.put(productMaterial.getWuliaocd(), orderMaterial);
                    }
                }
            }

        }

        List<OrderMaterial> orderMaterials = new ArrayList<>();
        for(String ddh : outerMap.keySet()) {
            Map<Integer,Map> middMap = outerMap.get(ddh);
            for(int wlbh : middMap.keySet()) {
                Map<Integer, OrderMaterial> inMap = middMap.get(wlbh);
                List<OrderMaterial> orderMaterialsTemp = new ArrayList<>();
                for (OrderMaterial orderMaterial : inMap.values()) {
                    orderMaterialsTemp.add(orderMaterial);
                    orderMaterialsTemp.sort(Comparator.comparingInt(OrderMaterial::getWuliaocd));
                }
                for(OrderMaterial om : orderMaterialsTemp) {
                    orderMaterials.add(om);
                }
            }
        }


        List<OrderMaterial> productListLimit = new ArrayList<>();
        int start = (page - 1) * limit;
        int count = 0;
        for(int i=0; i<orderMaterials.size();i++) {
            //start
            if(i >= start) {
                count++;
                productListLimit.add(orderMaterials.get(i));
            }
            if(count >= limit) {
                break;
            }

        }

        OrderRes productRes = new OrderRes();
        productRes.setCount(orderMaterials.size());
        productRes.setOrderMaterialList(productListLimit);
        System.out.println(productRes.toString());

        return Result.ok(productRes);
    }

    @GetMapping(value = "/getAllProductMaterial")
    public Result getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String dingdanh ) {
        System.out.println("page: " + page);
        System.out.println("page: " + limit);
        System.out.println("dingdanh: " + dingdanh);

        //获取订单列表
        List<OrderSimple> orderSimpleList = null;
        if(dingdanh != null && dingdanh.trim().length() > 0) {
            orderSimpleList = orderService.getOrderSimpleByDingdanh(dingdanh);
        } else {
            orderSimpleList = orderService.getAllOrderSimple();
        }
        System.out.println(orderSimpleList);

        List<Map<Integer, OrderMaterial>> resMapList = new ArrayList<>();
        Map<String, Map> outerMap = new HashMap<>(); //key是订单号
        for(OrderSimple orderSimple : orderSimpleList) {
            List<Order> orderList = orderService.getOrdersByDingdanh(orderSimple.getDingdanh());
            Map<Integer, Map> middleMap = new HashMap<>(); //key是物料编号
            outerMap.put(orderSimple.getDingdanh(), middleMap);
            for (Order order : orderList) {
                //根据订单下的产品，获取产品的物料信息，从而获得一个订单下各个物料需要的数量
                //A001 -- 1 -- 长度x
                //A001 -- 1 -- 长度y
                List<ProductMaterial> productList = productService.queryProductsByChanpdma(order.getChanpdma());
                for (ProductMaterial productMaterial : productList) {
                    int wuliaobh = productMaterial.getWuliaobh();
                    productMaterial.getWuliaocd();
                    productMaterial.getWuliaosl();

                    Map<Integer, OrderMaterial> innerMap = new HashMap<>(); //key是物料长度
                    if(middleMap.containsKey(wuliaobh)) {
                        innerMap = middleMap.get(wuliaobh);
                    } else {
                        middleMap.put(wuliaobh, innerMap);
                    }

                    if (innerMap.containsKey(productMaterial.getWuliaocd())) {
                        OrderMaterial orderMaterial = innerMap.get(productMaterial.getWuliaocd());
                        orderMaterial.setWuliaosl(orderMaterial.getWuliaosl() + productMaterial.getWuliaosl() * order.getChanpshl());
                    } else {
                        OrderMaterial orderMaterial = new OrderMaterial();
                        orderMaterial.setWuliaosl(productMaterial.getWuliaosl() * order.getChanpshl());
                        orderMaterial.setDingdanh(order.getDingdanh());
                        orderMaterial.setDingdanm(order.getDingdanm());
                        orderMaterial.setWuliaobh(productMaterial.getWuliaobh());
                        orderMaterial.setWuliaocd(productMaterial.getWuliaocd());
                        innerMap.put(productMaterial.getWuliaocd(), orderMaterial);
                    }
                }
            }

        }

        List<OrderMaterial> orderMaterials = new ArrayList<>();
        for(String ddh : outerMap.keySet()) {
            Map<Integer,Map> middMap = outerMap.get(ddh);
            for(int wlbh : middMap.keySet()) {
                Map<Integer, OrderMaterial> inMap = middMap.get(wlbh);
                List<OrderMaterial> orderMaterialsTemp = new ArrayList<>();
                for (OrderMaterial orderMaterial : inMap.values()) {
                    orderMaterialsTemp.add(orderMaterial);
                    orderMaterialsTemp.sort(Comparator.comparingInt(OrderMaterial::getWuliaocd));
                }
                for(OrderMaterial om : orderMaterialsTemp) {
                    orderMaterials.add(om);
                }
            }
        }


        List<OrderMaterial> productListLimit = new ArrayList<>();
        int start = (page - 1) * limit;
        int count = 0;
        for(int i=0; i<orderMaterials.size();i++) {
            //start
            if(i >= start) {
                count++;
                productListLimit.add(orderMaterials.get(i));
            }
            if(count >= limit) {
                break;
            }

        }

        OrderRes productRes = new OrderRes();
        productRes.setCount(orderMaterials.size());
        productRes.setOrderMaterialList(productListLimit);
        System.out.println(productRes.toString());

        List<PartRes> partResList = new ArrayList<>();
        for(String ddh : outerMap.keySet()) {
            Map<Integer,Map> middMap = outerMap.get(ddh);
            for(int wlbh : middMap.keySet()) {
                Map<Integer, OrderMaterial> inMap = middMap.get(wlbh);
                List<OrderMaterial> orderMaterialsTemp = new ArrayList<>();
                for (OrderMaterial orderMaterial : inMap.values()) {
                    orderMaterialsTemp.add(orderMaterial);
                    orderMaterialsTemp.sort(Comparator.comparingInt(OrderMaterial::getWuliaocd));
                }

                //对每一订单、物料做线性规划
                int len = orderMaterialsTemp.size();
                int[] part = new int[len];
                int[] constraints= new int[len];
                int idx = 0;
                for(OrderMaterial orderMaterial: orderMaterialsTemp) {
                    part[idx] = orderMaterial.getWuliaocd();
                    constraints[idx] = orderMaterial.getWuliaosl();
                    idx++;
                }

                Material material = materialService.findOneByWuliaobh(wlbh);
                int wuliaocd = material.getWuliaocd();
                List<List<Integer>> coefficients = new ArrayList<>(); //分割方案
                List<Integer> resVariables = new ArrayList<>();  //每种方案的数量

                Demo1Res demo1Res = seperateAndIntegerProgramService.run(wuliaocd, part, constraints, coefficients, resVariables);
                System.out.println("demo1Res-res: " + demo1Res.getRes().toString());
                List<PartRes> partResListTemp = new ArrayList<>();
                int iii = 1;
                for(PartRes partRes : demo1Res.getRes()) {
                    if(partRes.getNum() > 0) {
                        partRes.setIndex(iii++);
                        partRes.setDingdanh(ddh);
                        partRes.setWuliaobh(wlbh);
                        partRes.setWuliaocd(material.getWuliaocd());
                        partResListTemp.add(partRes);
                    }
                }

                for(PartRes partRes : partResListTemp) {
                    partResList.add(partRes);
                }
            }
        }

        productRes.setPartResList(partResList);
        productRes.setTotalPart(partResList.size());

        System.out.println(productRes);

        return Result.ok(productRes);
    }

}