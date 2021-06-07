package top.littlefogcat.leetcode;

public class P36_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] row = new int[10];
            int[] col = new int[10];
            int[] square = new int[10];
            for (int j = 0; j < 9; j++) {
                char rowC = board[i][j];
                char colC = board[j][i];
                char squC = board[i / 3 * 3 + j / 3][(i % 3) * 3 + j % 3];
                if (rowC != '.') {
                    row[rowC - '0']++;
                    if (row[rowC - '0'] > 1) return false;
                }
                if (colC != '.') {
                    col[colC - '0']++;
                    if (col[colC - '0'] > 1) return false;
                }
                if (squC != '.') {
                    square[squC - '0']++;
                    if (square[squC - '0'] > 1) return false;
                }
            }
        }
        return true;
    }
}
