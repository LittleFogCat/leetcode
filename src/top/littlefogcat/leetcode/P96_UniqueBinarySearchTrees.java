package top.littlefogcat.leetcode;

/**
 * 思路：同95题。空子树算一种情况。
 */
public class P96_UniqueBinarySearchTrees {

    public int numTrees(int n) {
        int[] dp = new int[20];
        dp[0] = 1; // 空子树算一种情况
        for (int i = 1; i <= n; i++) {
            // 计算dp[i]
            for (int j = 1; j <= i; j++) {
                // 以j为根节点
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
