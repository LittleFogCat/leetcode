package top.littlefogcat.leetcode;

public class P832_FlippingAnImage {
    class Solution {
        public int[][] flipAndInvertImage(int[][] A) {
            int itemInARow = A[0].length;
            int rows = A.length;
            int[][] B = new int[rows][itemInARow];
            for (int i = 0; i < rows; i++) {
                int[] aRow = A[i];
                for (int j = 0; j < (1 + aRow.length) / 2; j++) {
                    int left = aRow[j];
                    int right = aRow[aRow.length - 1 - j];
                    int tmp = 1 - left;
                    aRow[j] = 1 - right;
                    aRow[aRow.length - 1 - j] = tmp;
                }
                B[i] = aRow;
            }

            return B;
        }
    }
}
