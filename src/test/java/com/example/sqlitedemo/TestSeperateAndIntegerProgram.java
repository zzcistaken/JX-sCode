package com.example.sqlitedemo;

import com.example.demo.util.SeperateUtil;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import java.util.ArrayList;
import java.util.List;

public class TestSeperateAndIntegerProgram {

    public static void main (String[] args) {

        //给定一个原材料长度
        int n = 6000;

        //给定一种分段组合
        int[] part = {5404,2000,1740,1350,1200};

        //给定各个分段的需要的数量
        //Y = [124,135,45,100,50]

        //保存多少种切割方式
        //count = 0
        //seperate(n, 0, part)
        //print("count:", count)
        //print("loss:", loss)

        SeperateUtil seperateUtil = new SeperateUtil(n,part);
        seperateUtil.seperate();

        System.out.println(seperateUtil.getLossList());
        //[596, 0, 260, 650, 800, 520, 910, 1060, 100, 250, 400, 780, 1170, 120, 210, 360, 510, 660, 600, 750, 900, 1050, 0]
        //[596, 0, 260, 650, 800, 520, 910, 1060, 100, 250, 400, 780, 1170, 120, 210, 360, 510, 660, 600, 750, 900, 1050, 0]

        System.out.println(seperateUtil.getAllCount());
        //[[1, 0, 0, 0, 0], [0, 3, 0, 0, 0], [0, 2, 1, 0, 0], [0, 2, 0, 1, 0], [0, 2, 0, 0, 1], [0, 1, 2, 0, 0], [0, 1, 1, 1, 0], [0, 1, 1, 0, 1], [0, 1, 0, 2, 1], [0, 1, 0, 1, 2], [0, 1, 0, 0, 3], [0, 0, 3, 0, 0], [0, 0, 2, 1, 0], [0, 0, 2, 0, 2], [0, 0, 1, 3, 0], [0, 0, 1, 2, 1], [0, 0, 1, 1, 2], [0, 0, 1, 0, 3], [0, 0, 0, 4, 0], [0, 0, 0, 3, 1], [0, 0, 0, 2, 2], [0, 0, 0, 1, 3], [0, 0, 0, 0, 5]]
        //[[1, 0, 0, 0, 0], [0, 3, 0, 0, 0], [0, 2, 1, 0, 0], [0, 2, 0, 1, 0], [0, 2, 0, 0, 1], [0, 1, 2, 0, 0], [0, 1, 1, 1, 0], [0, 1, 1, 0, 1], [0, 1, 0, 2, 1], [0, 1, 0, 1, 2], [0, 1, 0, 0, 3], [0, 0, 3, 0, 0], [0, 0, 2, 1, 0], [0, 0, 2, 0, 2], [0, 0, 1, 3, 0], [0, 0, 1, 2, 1], [0, 0, 1, 1, 2], [0, 0, 1, 0, 3], [0, 0, 0, 4, 0], [0, 0, 0, 3, 1], [0, 0, 0, 2, 2], [0, 0, 0, 1, 3], [0, 0, 0, 0, 5]]

        //所有切割方案
        List<List<Integer>> allCount = seperateUtil.getAllCount();

        Loader.loadNativeLibraries(); // 确保加载本地库
        // 创建一个整数规划求解器
        MPSolver solver = new MPSolver(
                "IntegerProgrammingExample",
                MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

        //定义变量 x0 x1 ... x到切割方案的个数
        MPVariable[] variables = new MPVariable[allCount.size()];
        for(int i=0;i<variables.length;i++) {
            variables[i] = solver.makeIntVar(0, Integer.MAX_VALUE, "x"+i);
        }

        // 定义目标函数：maximize x0 + x1 + x2 + ... ，系数都是1
        MPObjective objective = solver.objective();
        for(int i=0;i<variables.length;i++) {
            objective.setCoefficient(variables[i], 1);
        }
        objective.setMinimization();

        //约束条件限制, 即每个长度的需要的数量
        int[] constraints = {124,135,45,100,50};
        //约束条件变量, 即多少种切割方案
        //[[1, 0, 0, 0, 0],
        // [0, 3, 0, 0, 0],
        // [0, 2, 1, 0, 0], [0, 2, 0, 1, 0], [0, 2, 0, 0, 1], [0, 1, 2, 0, 0], [0, 1, 1, 1, 0], [0, 1, 1, 0, 1], [0, 1, 0, 2, 1], [0, 1, 0, 1, 2], [0, 1, 0, 0, 3], [0, 0, 3, 0, 0], [0, 0, 2, 1, 0], [0, 0, 2, 0, 2], [0, 0, 1, 3, 0], [0, 0, 1, 2, 1], [0, 0, 1, 1, 2], [0, 0, 1, 0, 3], [0, 0, 0, 4, 0], [0, 0, 0, 3, 1], [0, 0, 0, 2, 2], [0, 0, 0, 1, 3], [0, 0, 0, 0, 5]]
        List<List<Integer>> coefficients = allCount;

        // 定义约束条件：
        //遍历5次
        for(int i=0; i<constraints.length;i++) {
            MPConstraint mpConstraint = solver.makeConstraint(constraints[i], Integer.MAX_VALUE);
            //遍历23次
            for(int ii=0;ii<coefficients.size();ii++) {
                List<Integer> coefficient = coefficients.get(ii);
                mpConstraint.setCoefficient(variables[ii], coefficient.get(i));
            }

        }

        // 求解问题
        solver.solve();

        // 输出结果
        System.out.println("Solution:");
        System.out.println("x = " + variables[0].solutionValue());
        System.out.println("y = " + variables[1].solutionValue());
        System.out.println("Maximum Objective Value: " + objective.value());
        int sum = 0;
        for(int i=0;i<variables.length;i++) {
            sum += variables[i].solutionValue();
            if(variables[i].solutionValue() > 0)
                System.out.println("x"+i+" = " + variables[i].solutionValue());
        }
        System.out.println("sum: " + sum);

        /*
        x0:  124.0
        x1:  42.0
        最小值:  216.0
        sum:  216.0
        sumLoss:  82604.0
        */

    }
}
