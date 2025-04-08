package top.littlefogcat.leetcode;

public class P72_EditDistance {
    /**
     * 思路：直接模拟修改过程，穷举所有结果。
     * 使用记忆化DP来简化复杂度。
     */
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        char[] cs1 = word1.toCharArray();
        char[] cs2 = word2.toCharArray();
        return minDist(cs1, cs2, 0, 0, new int[cs1.length][cs2.length]);
    }

    /**
     * 从i1、i2位置开始的子字符串转换需要的操作数。
     */
    private int minDist(char[] cs1, char[] cs2, int i1, int i2, int[][] dp) {
        if (i1 == cs1.length || i2 == cs2.length) {
            // 子字符串为空，则只能通过添加操作补全，操作数为两字符串长度之差
            return cs1.length + cs2.length - i1 - i2;
        }
        if (dp[i1][i2] != 0) return dp[i1][i2]; // 已保存结果
        int count;
        if (cs1[i1] == cs2[i2]) {
            // 第一个字符相同，则不需要转换
            count = minDist(cs1, cs2, i1 + 1, i2 + 1, dp);
        } else {
            // 第一个字符不同，模拟替换、添加、删除三种操作，并取最小操作数
            int replace = 1 + minDist(cs1, cs2, i1 + 1, i2 + 1, dp);
            int add = 1 + minDist(cs1, cs2, i1, i2 + 1, dp);
            int remove = 1 + minDist(cs1, cs2, i1 + 1, i2, dp);
            count = Math.min(replace, Math.min(add, remove));
        }
        dp[i1][i2] = count; // 保存结果
        return count;
    }
}
