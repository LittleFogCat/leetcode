package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P64_MinimumPathSum {
    public int minPathSum1(int[][] grid) {
        int[] lastLine = new int[grid[0].length];
        // 初始化最后一行
        int lastRow = grid.length - 1;
        lastLine[lastLine.length - 1] = grid[lastRow][lastLine.length - 1];
        for (int i = lastLine.length - 2; i >= 0; i--) {
            lastLine[i] = grid[lastRow][i] + lastLine[i + 1];
        }
        minPathSum(grid, lastLine, grid.length - 1, lastLine.length - 1);
        return lastLine[0];
    }

    private void minPathSum(int[][] grid, int[] lastLine, int row, int col) {
        if (col == grid[0].length - 1) { // 最右列
            lastLine[col] = grid[row][col] + lastLine[col];
        } else { // 往右走+往下走
            lastLine[col] = grid[row][col] + Math.min(lastLine[col], lastLine[col + 1]);
        }
        if (col != 0) { // 继续左边一格
            minPathSum(grid, lastLine, row, col - 1);
        } else if (row != 0) { // 继续上一行
            minPathSum(grid, lastLine, row - 1, grid[0].length - 1);
        }
    }

    /**
     * 非递归做法
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] dp = grid[rows - 1];
        for (int i = cols - 2; i >= 0; i--) { // 最后一行
            dp[i] += dp[i + 1];
        }
        for (int i = rows - 2, j = cols - 1; i >= 0; ) {
            dp[j] = j == cols - 1 ? grid[i][j] + dp[j] : grid[i][j] + Math.min(dp[j], dp[j + 1]);
            if (j != 0) j--;
            else {
                j = cols - 1;
                i--;
            }
        }
        return dp[0];
    }
}
