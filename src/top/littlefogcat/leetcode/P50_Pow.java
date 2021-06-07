package top.littlefogcat.leetcode;

public class P50_Pow {
    public double myPow(double x, int n) {
        return n == 0 ? 1 : n == 1 ? x : n == -1 ? 1 / x : n == 2 ? x * x : myPow(myPow(x, n >> 1), 2) * ((n & 1) == 0 ? 1 : x);
    }
}
