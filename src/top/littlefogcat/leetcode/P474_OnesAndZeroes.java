package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P474_OnesAndZeroes {

    // ------------------------动态规划递归------------------------------

    public int findMaxForm2(String[] strs, int m, int n) {
        return findMaxForm2(strs, m, n, 0);
    }

    public int findMaxForm2(String[] strs, int m, int n, int current) {
        if (current >= strs.length) return 0;
        int zeros = 0, ones = 0;
        for (int i = 0; i < strs[current].length(); i++) {
            if (strs[current].charAt(i) == '1') ones++;
            else zeros++;
        }
        int without = findMaxForm2(strs, m, n, current + 1); // 不包含当前项
        if (zeros > m || ones > n) return without; // 当前项的0/1个数不满足条件
        int with = 1 + findMaxForm2(strs, m - zeros, n - ones, current + 1); // 包含当前项
        return Math.max(without, with);
    }

    // ------------------------动态规划递归------------------------------

    public int findMaxForm1(String[] strs, int m, int n) {
        return findMaxForm1(strs, m, n, 0, new int[m + 1][n + 1][strs.length]);
    }

    public int findMaxForm1(String[] strs, int m, int n, int current, int[][][] dp) {
        if (current >= strs.length) return 0;
        if (dp[m][n][current] != 0) return dp[m][n][current];
        int zeros = 0, ones = 0;
        for (int i = 0; i < strs[current].length(); i++) {
            if (strs[current].charAt(i) == '1') ones++;
            else zeros++;
        }
        int without = findMaxForm1(strs, m, n, current + 1, dp); // 不包含当前项
        if (zeros > m || ones > n) dp[m][n][current] = without; // 当前项的0/1个数不满足条件
        else {
            int with = 1 + findMaxForm1(strs, m - zeros, n - ones, current + 1, dp); // 包含当前项
            dp[m][n][current] = Math.max(without, with);
        }
        return dp[m][n][current];
    }

    public int findMaxForm3(String[] strs, int m, int n) {
        return findMaxForm3(strs, m, n, 0, new int[m + 1][n + 1][strs.length]);
    }

    public int findMaxForm3(String[] strs, int m, int n, int current, int[][][] dp) {
        if (current >= strs.length) return 0;
        if (dp[m][n][current] != 0) return dp[m][n][current];
        int zeros = 0, ones = 0;
        for (int i = 0; i < strs[current].length(); i++) {
            if (strs[current].charAt(i) == '1') ones++;
            else zeros++;
        }
        int without = findMaxForm3(strs, m, n, current + 1, dp); // 不包含当前项
        if (zeros > m || ones > n) dp[m][n][current] = without; // 当前项的0/1个数不满足条件
        else {
            int with = 1 + findMaxForm3(strs, m - zeros, n - ones, current + 1, dp); // 包含当前项
            dp[m][n][current] = Math.max(without, with);
        }
        return dp[m][n][current];
    }
//
//      上述解法状态转移方程式：
//      dp[m][n][c] = Max(dp[m][n][c+1], 1 + dp[m-z][n-o][c+1])
//
//      其中z是0的个数，o是1的个数。
//
//      可以发现，数组第三项c只与c+1相关，所以该项可以省略，变为二维数组。
//
//      c 从 str.length - 1 循环到 0，最终的dp[m][n]则为所求结果
//

    // ------------------------动态规划递推------------------------------

    public int findMaxForm(String[] strs, int M, int N) {
        // 计算各个字符串0/1个数
        int[] zeros = new int[strs.length]; // 0和1的个数
        int[] ones = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '1') ones[i]++;
                else zeros[i]++;
            }
        }
        // 初始化二维dp数组
        int[][] dp = new int[M + 1][N + 1];
        // 递推
        for (int c = strs.length - 1; c >= 0; c--) {
            for (int m = dp.length - 1; m >= 0; m--) {
                if (m < zeros[c]) break;
                for (int n = dp[0].length - 1; n >= 0; n--) {
                    if (n < ones[c]) break;
                    dp[m][n] = Math.max(dp[m][n], 1 + dp[m - zeros[c]][n - ones[c]]);
                }
            }
        }
//        Util.printMatrix(dp);
        return dp[M][N];
    }

    public static void main(String[] args) {
        P474_OnesAndZeroes p = new P474_OnesAndZeroes();
        String[] s = new String[]{"11", "11", "0", "0", "10", "1", "1", "0", "11", "1", "0", "111", "11111000", "0", "11", "000", "1", "1", "0", "00", "1", "101", "001", "000", "0", "00", "0011", "0", "10000"};
        int m = 90;
        int n = 66;
        System.out.println(s.length);
        int r = p.findMaxForm(s, m, n);
        System.out.println(r);
    }

}
