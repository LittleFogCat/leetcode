package top.littlefogcat.leetcode;

public class P1342_NumberOfStepsToReduceANumberToZero {
    public int numberOfSteps(int num) {
        for (int step = 0; ; num = (num & 1) == 0 ? num >> 1 : num ^ 1, step++) if (num == 0) return step;
    }
}
