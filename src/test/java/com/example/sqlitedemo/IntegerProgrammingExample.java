package com.example.sqlitedemo;

import com.google.ortools.Loader;
import com.google.ortools.init.OrToolsVersion;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import java.util.ArrayList;
import java.util.List;

public class IntegerProgrammingExample {
    public static void main(String[] args) {

        Loader.loadNativeLibraries(); // 确保加载本地库
        demo3();

    }

    public static void demo1() {
        // 创建一个整数规划求解器
        MPSolver solver = new MPSolver(
                "IntegerProgrammingExample",
                MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

        // 定义变量 x 和 y，它们必须是整数
        MPVariable x = solver.makeIntVar(0, Integer.MAX_VALUE, "x");
        MPVariable y = solver.makeIntVar(0, Integer.MAX_VALUE, "y");

        // 定义目标函数：maximize 3x + 4y
        MPObjective objective = solver.objective();
        objective.setCoefficient(x, 3.0);
        objective.setCoefficient(y, 4.0);
        objective.setMaximization();

        // 定义约束条件：
        // x + 2y <= 20
        MPConstraint c1 = solver.makeConstraint(-Double.POSITIVE_INFINITY, 20.0);
        c1.setCoefficient(x, 1.0);
        c1.setCoefficient(y, 2.0);

        // 3x + 2y <= 30
        MPConstraint c2 = solver.makeConstraint(-Double.POSITIVE_INFINITY, 30.0);
        c2.setCoefficient(x, 3.0);
        c2.setCoefficient(y, 2.0);

        // 求解问题
        solver.solve();

        // 输出结果
        System.out.println("Solution:");
        System.out.println("x = " + x.solutionValue());
        System.out.println("y = " + y.solutionValue());
        System.out.println("Maximum Objective Value: " + objective.value());
    }

    public static void demo2() {
        // 创建一个整数规划求解器
        MPSolver solver = new MPSolver(
                "IntegerProgrammingExample",
                MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

        // 定义变量 x 和 y，它们必须是整数
        MPVariable x = solver.makeIntVar(0, Integer.MAX_VALUE, "x");
        MPVariable y = solver.makeIntVar(0, Integer.MAX_VALUE, "y");

        // 定义目标函数：maximize x + 2*y
        MPObjective objective = solver.objective();
        objective.setCoefficient(x, 1.0);
        objective.setCoefficient(y, 2.0);
        objective.setMinimization();

        // 定义约束条件：
        // 2*x + y >= 10
        MPConstraint c1 = solver.makeConstraint(10, Integer.MAX_VALUE);
        c1.setCoefficient(x, 2.0);
        c1.setCoefficient(y, 1.0);

        // x + 3*y >= 15
        MPConstraint c2 = solver.makeConstraint(15, Integer.MAX_VALUE);
        c2.setCoefficient(x, 1.0);
        c2.setCoefficient(y, 3.0);

        // 求解问题
        solver.solve();

        // 输出结果
        System.out.println("Solution:");
        System.out.println("x = " + x.solutionValue());
        System.out.println("y = " + y.solutionValue());
        System.out.println("Maximum Objective Value: " + objective.value());

        /*
        Solution:
        x = 3.0
        y = 4.0
        Maximum Objective Value: 11.0
         */
    }

    public static void demo3() {
        // 创建一个整数规划求解器
        MPSolver solver = new MPSolver(
                "IntegerProgrammingExample",
                MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

        // 定义变量 x 和 y，它们必须是整数
//        MPVariable x = solver.makeIntVar(0, Integer.MAX_VALUE, "x");
//        MPVariable y = solver.makeIntVar(0, Integer.MAX_VALUE, "y");
        MPVariable[] variables = new MPVariable[2];
        for(int i=0;i<variables.length;i++) {
            variables[i] = solver.makeIntVar(0, Integer.MAX_VALUE, "x"+i);
        }

        // 定义目标函数：maximize x + 2*y
        int[] objectiveParams = {1,2};
        MPObjective objective = solver.objective();
//        objective.setCoefficient(x, 1.0);
//        objective.setCoefficient(y, 2.0);
        for(int i=0;i<variables.length;i++) {
            objective.setCoefficient(variables[i], objectiveParams[i]);
        }
        objective.setMinimization();


        //约束条件限制
        int[] constraints = {10, 15};
        //约束条件变量
        List<List> coefficients = new ArrayList<>(2);
        List<Integer> coefficient0 = new ArrayList<>(2);
        coefficient0.add(2);
        coefficient0.add(1);
        List<Integer> coefficient1 = new ArrayList<>(2);
        coefficient1.add(1);
        coefficient1.add(3);
        coefficients.add(coefficient0);
        coefficients.add(coefficient1);

        // 定义约束条件：
        for(int i=0; i<constraints.length;i++) {
            MPConstraint mpConstraint = solver.makeConstraint(constraints[i], Integer.MAX_VALUE);
            List<Integer> coefficient = coefficients.get(i);
            for(int j=0;j<coefficient.size();j++) {
                mpConstraint.setCoefficient(variables[j], coefficient.get(j));
            }
        }
        // 2*x + y >= 10
//        MPConstraint c1 = solver.makeConstraint(10, Integer.MAX_VALUE);
//        c1.setCoefficient(x, 2.0);
//        c1.setCoefficient(y, 1.0);

        // x + 3*y >= 15
//        MPConstraint c2 = solver.makeConstraint(15, Integer.MAX_VALUE);
//        c2.setCoefficient(x, 1.0);
//        c2.setCoefficient(y, 3.0);

        // 求解问题
        solver.solve();

        // 输出结果
        System.out.println("Solution:");
        System.out.println("x = " + variables[0].solutionValue());
        System.out.println("y = " + variables[1].solutionValue());
        System.out.println("Maximum Objective Value: " + objective.value());

        /*
        Solution:
        x = 3.0
        y = 4.0
        Maximum Objective Value: 11.0
         */
    }
}

