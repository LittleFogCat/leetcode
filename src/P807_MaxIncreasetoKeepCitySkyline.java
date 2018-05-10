
public class P807_MaxIncreasetoKeepCitySkyline {
    // TODO: 2018/5/10 There should be a solution that only pass through the array only once
    class Solution {
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
}
