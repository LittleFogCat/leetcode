package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 思路：某一个点的最小路径和 = 该点的值 + min(左下角最小路径和, 右下角最小路径和)
 * 状态转移方程：
 * dp[i][j] = value[i][j] + min(dp[i+1][j], dp[i+1][j+1])
 */
public class P120_Triangle {
    /**
     * 递归方式
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotal(triangle, 0, 0, new Integer[triangle.size()][triangle.size()]);
    }

    private int minimumTotal(List<List<Integer>> triangle, int level, int index, Integer[][] dp) {
        if (dp[level][index] != null) return dp[level][index];
        if (level == triangle.size() - 1) {
            dp[level][index] = triangle.get(level).get(index);
        } else {
            dp[level][index] = triangle.get(level).get(index) + Math.min(
                    minimumTotal(triangle, level + 1, index, dp),
                    minimumTotal(triangle, level + 1, index + 1, dp)
            );
        }
        return dp[level][index];
    }

    /**
     * 迭代方式 + 一维记忆数组
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        Integer[] dp = new Integer[triangle.size()];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = triangle.get(triangle.size() - 1).get(i);
        }
        for (int level = triangle.size() - 2; level >= 0; level--) {
            List<Integer> line = triangle.get(level);
            for (int i = 0; i <= level; i++) {
                dp[i] = line.get(i) + Math.min(dp[i], dp[i + 1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> t = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            List<Integer> level = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int r = new Random().nextInt(100);
                level.add(r);
            }
            t.add(level);
        }
        P120_Triangle p = new P120_Triangle();
        long t1 = TimeCounter.countNano(() -> p.minimumTotal(t));
        long t2 = TimeCounter.countNano(() -> p.minimumTotal1(t));

        System.out.println("recurse: "+t1);
        System.out.println("iterate: "+t2);
    }
}
