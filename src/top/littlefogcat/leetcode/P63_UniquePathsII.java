package top.littlefogcat.leetcode;

public class P63_UniquePathsII {

    /**
     * 记忆化动态规划
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[] dp = new int[obstacleGrid[0].length];
        uniquePathsWithObstacles(obstacleGrid, dp, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
        return dp[0];
    }

    private void uniquePathsWithObstacles(int[][] obstacleGrid, int[] dp, int row, int col) {
        if (obstacleGrid[row][col] == 1) { // 障碍
            dp[col] = 0;
        } else if (row == obstacleGrid.length - 1 && col == obstacleGrid[0].length - 1) {
            dp[col] = 1;
        } else if (row == obstacleGrid.length - 1) { // 最后一行
            dp[col] = dp[col + 1];
        } else if (col == obstacleGrid[0].length - 1) { // 最右列
            dp[col] = dp[col];
        } else { // 往右走+往下走
            dp[col] = dp[col] + dp[col + 1];
        }
        if (col != 0) { // 继续左边一格
            uniquePathsWithObstacles(obstacleGrid, dp, row, col - 1);
        } else if (row != 0) { // 继续上一行
            uniquePathsWithObstacles(obstacleGrid, dp, row - 1, obstacleGrid[0].length - 1);
        }
    }
}
