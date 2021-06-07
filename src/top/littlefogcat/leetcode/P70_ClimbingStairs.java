package top.littlefogcat.leetcode;

public class P70_ClimbingStairs {
    /**
     * 经典动态规划
     * <p>
     * f(n) = f(n-1) + f(n-2)
     * <p>
     * 等同于求斐波拉契数列
     */
    public int climbStairs(int n) {
        if (n < 3) return n;

        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 2;
        while (n-- > 2) {
            dp[1] = dp[0] + dp[1];
            dp[0] = dp[1] - dp[0];
        }
        return dp[1];
    }
}
