package top.littlefogcat.leetcode;

public class P62_UniquePaths {
    int[][] dp;

    public int uniquePaths0(int m, int n) {
        dp = new int[m + 1][n + 1];
        return dp(m, n);
    }

    private int dp(int m, int n) {
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        if (m == 1 || n == 1) {
            dp[m][n] = 1;
            return 1;
        }
        dp[m][n] = dp(m - 1, n) + dp(m, n - 1);
        return dp[m][n];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][1] = 1;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[1][j] = 1;
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = 2; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        P62_UniquePaths p = new P62_UniquePaths();
        System.out.println(p.uniquePaths(51, 9));
    }
}
