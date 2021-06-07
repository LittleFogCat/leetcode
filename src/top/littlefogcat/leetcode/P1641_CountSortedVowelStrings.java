package top.littlefogcat.leetcode;

import static java.lang.Math.pow;

public class P1641_CountSortedVowelStrings {
    private int[][] dp;

    public int countVowelStrings(int n) {
        dp = new int[n + 1][6];
        return helper(n, 5);
    }

    // s代表接下来的字符可选的个数
    private int helper(int n, int s) {
        if (dp[n][s] != 0) return dp[n][s];
        if (n == 1) return s;
        int count = 0;
        for (int i = 1; i <= s; i++) {
            count += helper(n - 1, i);
        }
        dp[n][s] = count;
        return count;
    }

    public int countVowelStrings0(int n) {
        return 1 + (pow(n, 3) + 10 * pow(n, 2) + 35 * n + 50) * n / 24;
    }

    public int pow(int a, int b) {
        return (int) Math.pow(a, b);
    }

    public static void main(String[] args) {
        P1641_CountSortedVowelStrings p = new P1641_CountSortedVowelStrings();
        for (int i = 1; i < 34; i++) {
            int cvs = p.countVowelStrings(i);
            int cvs0 = p.countVowelStrings0(i);
            System.out.println(i + ", " + cvs + ", " + cvs0);
//            System.out.print(i + ", " + p.countVowelStrings(i));
//            System.out.print(", " + (p.countVowelStrings(i + 1) - p.countVowelStrings(i)));
//            System.out.println(", " + ((p.countVowelStrings(i + 2) - p.countVowelStrings(i + 1)) - (p.countVowelStrings(i + 1) - p.countVowelStrings(i))));
        }
    }
}
