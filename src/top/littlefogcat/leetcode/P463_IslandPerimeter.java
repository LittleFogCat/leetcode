package top.littlefogcat.leetcode;

public class P463_IslandPerimeter {
    class Solution {
        public int islandPerimeter(int[][] grid) {
            int rows = grid.length;
            int columns = grid[0].length;
            int islandPerimeter = 0;
            for (int i = 0; i < rows; i++) {
                int[] aRow = grid[i];
                for (int j = 0; j < columns; j++) {
                    int aInt = aRow[j];
                    // 考虑边界
                    if (aInt == 1) {
                        if (i == 0) {
                            islandPerimeter++;
                        }
                        if (j == 0) {
                            islandPerimeter++;
                        }
                        if (i == rows - 1) {
                            islandPerimeter++;
                        }
                        if (j == columns - 1) {
                            islandPerimeter++;
                        }
                    }
                    // 考虑内部
                    // 和上一行不同，++
                    if (i != 0 && aInt != grid[i - 1][j]) {
                        islandPerimeter++;
                    }
                    // 和上一列不同
                    if (j != 0 && aInt != grid[i][j - 1]) {
                        islandPerimeter++;
                    }
                }
            }

            return islandPerimeter;
        }
    }
}
