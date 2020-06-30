package top.littlefogcat.leetcode;

public class P883_ProjectionAreaOf3DShapes {
    public static void main(String[] args) {
        Solution solution = new P883_ProjectionAreaOf3DShapes().new Solution();
        int[][] grid = {{1, 0}, {0, 2}};
        System.out.println(solution.projectionArea(grid));
    }

    class Solution {
        public int projectionArea(int[][] grid) {
            int sum = 0;

            for (int[] aGrid : grid) {
                int columnMax = aGrid[0];
                for (int j = 0; j < grid[0].length; j++) {
                    if (columnMax < aGrid[j]) {
                        columnMax = aGrid[j];
                    }
                }
                sum += columnMax;
            }
            for (int i = 0; i < grid[0].length; i++) {
                int columnMax = grid[0][i];
                for (int j = 0; j < grid[0].length; j++) {
                    if (columnMax < grid[j][i]) {
                        columnMax = grid[j][i];
                    }
                    if (grid[j][i] != 0) {
                        sum++;
                    }
                }
                sum += columnMax;
            }
            return sum;
        }
    }
}
