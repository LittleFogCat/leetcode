package top.littlefogcat.leetcode;

public class P566_ReshapeTheMatrix {
    class Solution {
        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int rows = nums.length;
            int columns = nums[0].length;
            if (rows * columns != r * c) return nums;
            int[][] result = new int[r][c];
            int row = 0, column = 0;

            for (int[] num : nums) {
                for (int j = 0; j < columns; j++) {
                    int aInt = num[j];
                    result[row][column] = aInt;
                    if (column == c - 1) {
                        column = 0;
                        row++;
                    } else {
                        column++;
                    }
                }
            }

            return result;
        }
    }
}
