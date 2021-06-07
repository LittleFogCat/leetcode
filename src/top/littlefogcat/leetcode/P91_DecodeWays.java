package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P91_DecodeWays {
    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return numDecodings(s.toCharArray(), 0, dp);
    }

    // dp[i]: 以i为起点的解个数
    private int numDecodings(char[] s, int index, int[] dp) {
        if (index == s.length) return 1; // 边界条件
        if (dp[index] != -1) return dp[index]; // 已经计算过了
        else if (s[index] == '0') dp[index] = 0; // 不可0开头
        else if (index == s.length - 1) dp[index] = 1; // 最后一位
        else if (10 * s[index] + s[index + 1] > 554) dp[index] = numDecodings(s, index + 1, dp); // 接下来2位大于26
        else dp[index] = numDecodings(s, index + 1, dp) + numDecodings(s, index + 2, dp); // 接下来2位小等26
        return dp[index];
    }

    public static void main(String[] args) {
        P91_DecodeWays p = new P91_DecodeWays();
        assert p.numDecodings("2101") == 1;
        assert p.numDecodings("27") == 1;
        assert p.numDecodings("226") == 3;
        assert p.numDecodings("0226") == 0;
        assert p.numDecodings("20026") == 0;
        assert p.numDecodings("0") == 0;
        assert p.numDecodings("111111111111111111111111111111111111111111111") == 1836311903;
    }
}
