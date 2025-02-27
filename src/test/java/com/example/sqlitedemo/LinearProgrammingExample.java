package com.example.sqlitedemo;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class LinearProgrammingExample {

    public static void main(String[] args) {
        // 目标函数：maximize 3x + 4y
        double[] coefficients = {3, 4};
        LinearObjectiveFunction f = new LinearObjectiveFunction(coefficients, 0);

        // 约束条件：
        // x + 2y ≤ 20
        // 3x + 2y ≤ 50
        // x ≥ 0, y ≥ 0
        LinearConstraint[] constraints = {
                new LinearConstraint(new double[]{1, 2}, Relationship.LEQ, 20),
                new LinearConstraint(new double[]{3, 2}, Relationship.LEQ, 50)
        };

        // 定义优化问题
        LinearConstraintSet constraintSet = new LinearConstraintSet(constraints);
        NonNegativeConstraint nonNegativeConstraint = new NonNegativeConstraint(true);

        // 使用单纯形算法求解
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution;
        try {
            solution = solver.optimize(new MaxIter(100), f, GoalType.MAXIMIZE, constraintSet, nonNegativeConstraint);
            System.out.println("Optimal value: " + solution.getValue());
            System.out.println("x = " + solution.getPoint()[0]);
            System.out.println("y = " + solution.getPoint()[1]);
        } catch (NoFeasibleSolutionException e) {
            System.out.println("No feasible solution found.");
        }
    }


}
