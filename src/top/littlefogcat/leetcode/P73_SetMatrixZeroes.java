package top.littlefogcat.leetcode;

import static top.littlefogcat.leetcode.utils.Util.matrixToString;

public class P73_SetMatrixZeroes {
    public void setZeroes1(int[][] matrix) {
        int changeTo = -381746178;
        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            for (int j = 0; j < row.length; j++) {
                changeIfZero(matrix, i, j, changeTo);
            }
        }
        for (int[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                if (row[j] == changeTo) {
                    row[j] = 0;
                }
            }
        }
    }

    private void changeIfZero(int[][] mat, int row, int col, int changeTo) {
        if (mat[row][col] != 0) return;
        // row:
        for (int i = 0; i < mat[0].length; i++) {
            if (mat[row][i] != 0) {
                mat[row][i] = changeTo;
            }
        }
        // col:
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][col] != 0) {
                mat[i][col] = changeTo;
            }
        }
        mat[row][col] = changeTo;
    }

    public void setZeroes(int[][] matrix) {
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRowHasZero) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstColHasZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
