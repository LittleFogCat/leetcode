public class P807_MaxIncreasetoKeepCitySkyline {
    class Solution {
        private int[] rowMax;
        private int[] columnMax;

        public int maxIncreaseKeepingSkyline(int[][] grid) {
            rowMax = new int[grid[0].length];
            columnMax = new int[grid.length];
            for (int i = 0; i < grid.length; i++) {
                int aRow[] = grid[i];

                for (int j = 0; j < aRow.length; j++) {
                    int aInt = aRow[j]; // a int at (j, i)

                }
            }

            return 0;
        }
    }
}
