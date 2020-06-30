package top.littlefogcat.leetcode;

public class P766_ToeplitzMatrix {
    class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            int rows = matrix.length;
            int columns = matrix[0].length;
            int end = Math.min(rows, columns);
            for (int column = columns - 2; ; column--) {
                if (column == -rows) break;
                // 确定起点
                int startRow;
                int startColumn;
                if (column >= 0) {
                    startColumn = column;
                    startRow = 0;
                } else {
                    startRow = -column;
                    startColumn = 0;
                }
                int baseNum = matrix[startRow][startColumn];
                int maxI = Math.min(rows - startRow, columns - startColumn);
                System.out.println("baseNum = " + baseNum);
                System.out.println("maxI = " + maxI);
                System.out.println("SR = " + startRow);
                System.out.println("SC = " + startColumn);
                for (int i = 1; i < maxI; i++) { // startRow + i < rows && startColumn + i < columns
                    if (matrix[startRow + i][startColumn + i] != baseNum) return false;
                }
            }
            return true;
        }
    }

    class Solution2 {
        public boolean isToeplitzMatrix(int[][] matrix) {
            int[] lastRow = matrix[0];
            for (int i = 1; i < matrix.length; i++) {
                int[] aRow = matrix[i];
                for (int j = 1; j < aRow.length; j++) {
                    if (aRow[j] != lastRow[j - 1]) {
                        return false;
                    }
                }
                lastRow = aRow;
            }
            return true;
        }
    }
}
