package top.littlefogcat.leetcode;

public class P807_MaxIncreasetoKeepCitySkyline {
    // TODO: 2018/5/10 There may be a solution that only pass through the array only once, but how?
    class Solution1 {
        private int[] rowMax; // max row contains max nums from each column
        private int[] columnMax; // max column contains max nums from each row

        public int maxIncreaseKeepingSkyline(int[][] grid) {
            rowMax = new int[grid[0].length];
            columnMax = new int[grid.length];
            int increase = 0;
            for (int i = 0; i < grid.length; i++) {
                int aRow[] = grid[i];

                for (int j = 0; j < aRow.length; j++) {
                    int aInt = aRow[j]; // a int at (j, i), no.i row and no.j column
                    if (aInt > columnMax[j]) {
                        columnMax[j] = aInt;
                    }
                    if (aInt > rowMax[i]) {
                        rowMax[i] = aInt;
                    }
                }
            }
            for (int i = 0; i < grid.length; i++) {
                int aRow[] = grid[i];

                for (int j = 0; j < aRow.length; j++) {
                    int aInt = aRow[j]; // a int at (j, i), no.i row and no.j column
                    int rm = rowMax[i];
                    int cm = columnMax[j];
                    if (cm > rm) {
                        increase += rm - aInt;
                    } else {
                        increase += cm - aInt;
                    }
                }
            }

            return increase;
        }
    }

    /**
     * We can calculate the current sum of all buildings and target sum of all buildings,
     * then the diff of them is increase.
     * Actually this solution is almost the same with Solution1. MMP.
     */
    class Solution2 {
        private int[] rowMax; // max row contains max nums from each column
        private int[] columnMax; // max column contains max nums from each row

        public int maxIncreaseKeepingSkyline(int[][] grid) {
            rowMax = new int[grid[0].length];
            columnMax = new int[grid.length];
            int increase = 0;
            for (int i = 0; i < grid.length; i++) {
                int aRow[] = grid[i];

                for (int j = 0; j < aRow.length; j++) {
                    int aInt = aRow[j]; // a int at (j, i), no.i row and no.j column
                    increase += aInt;
                    if (aInt > columnMax[j]) {
                        columnMax[j] = aInt;
                    }
                    if (aInt > rowMax[i]) {
                        rowMax[i] = aInt;
                    }
                }
            }
            increase = -increase;

            for (int i = 0; i < grid.length; i++) {
                int aRow[] = grid[i];

                for (int j = 0; j < aRow.length; j++) {
                    increase += columnMax[i] > rowMax[j] ? rowMax[j] : columnMax[i];
                }
            }

            return increase;
        }
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] rowMax = new int[grid.length];
        int[] colMax = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int height = grid[i][j];
                if (height > rowMax[i]) rowMax[i] = height;
                if (height > colMax[j]) colMax[j] = height;
            }
        }
        int diff = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                diff += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return diff;
    }
}
