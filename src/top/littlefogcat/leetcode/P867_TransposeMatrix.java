package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P867_TransposeMatrix {

    public static void main(String[] args) {
        Solution solution = new P867_TransposeMatrix().new Solution();
        int[][] test = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(solution.transpose(test)));
    }

    class Solution {
        public int[][] transpose(int[][] A) {
            int rows = A.length;// row number of `rows`
            int columns = A[0].length;// column number of `columns`

            int[][] B = new int[columns][rows];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    B[j][i] = A[i][j];
                }
            }

            return B;
        }
    }
}
