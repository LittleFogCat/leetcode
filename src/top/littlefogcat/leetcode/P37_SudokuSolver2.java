package top.littlefogcat.leetcode;

public class P37_SudokuSolver2 {
    private static final int INIT_VALUE = 0b1111111110;
    private char[][] orig;
    private final int[][] board = new int[9][9];
    private final int[][] restore = new int[9][9];

    public void solveSudoku(char[][] orig) {
        this.orig = orig;
        for (int i = 0; i < orig.length; i++) {
            for (int j = 0; j < orig[0].length; j++) {
                if (orig[i][j] == '.') {
                    restore[i][j] = board[i][j] = INIT_VALUE;
                } else {
                    restore[i][j] = board[i][j] = 1 << (orig[i][j] - '0');
                }
            }
        }
        solve();
    }

    public void solve() {
        // init
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (count(board[i][j]) == 1) continue;
                    int v = makePossibleValue(i, j);
                    if (count(v) == 1) flag = true;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, restore[i], 0, 9);
        }
        solve(0, 0);
    }

    /**
     * 填(row, col)
     */
    private boolean solve(int row, int col) {
        // 计算 board[row][col] 可能的数字
        int possible = makePossibleValue(row, col);
        if (count(possible) == 0) { // 没有可以填的数字，说明之前填错了，应回溯。
            return false;
        }
        for (int n = 1; n <= 9; n++) {
            if ((possible & (1 << n)) == 0) continue;
            board[row][col] = 1 << n;
            int[] next = next(row, col);
            if (next[0] == -1) {
                onComplete();
                return true;
            }
            if (solve(next[0], next[1])) return true;
        }
        board[row][col] = restore[row][col]; // 当前格子所有可能都失败，回溯
        return false;
    }

    private int[] next(int row, int col) {
        int[] rc = new int[]{-1, -1};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (count(board[i][j]) == 1 || i == row && j == col) continue;
                if (rc[0] == -1 || count(board[i][j]) < count(board[rc[0]][rc[1]])) {
                    rc[0] = i;
                    rc[1] = j;
                }
            }
        }
        return rc;
    }

    private int count(int i) {
        return Integer.bitCount(i);
    }

    private int makePossibleValue(int row, int col) {
        // i行j列
        board[row][col] = restore[row][col];
        // 对于一个格子，如果在其行/列/块中有某个数，则在备选项中删除这个数；
        for (int i = 0; i < 9; i++) {
            if (col != i && count(board[row][i]) == 1) {
                remove(row, col, row, i);
            }
            if (i != row && count(board[i][col]) == 1) {
                remove(row, col, i, col);
            }
            int otherRow = row / 3 * 3 + i / 3;
            int otherCol = col / 3 * 3 + i % 3;
            if ((otherRow != row || otherCol != col) && count(board[otherRow][otherCol]) == 1) {
                remove(row, col, otherRow, otherCol);
            }
        }
        return board[row][col];
    }

    private void onComplete() {
        for (int i = 0; i < orig.length; i++) {
            for (int j = 0; j < orig[i].length; j++) {
                orig[i][j] = (char) ('0' + first(board[i][j]));
            }
        }
    }

    public int first(int value) {
        for (int i = 1; i <= 9; i++) if ((value & (1 << i)) != 0) return i;
        return -1;
    }

    public void remove(int i, int j, int i1, int j1) {
        board[i][j] &= (~board[i1][j1]);
    }

    public static void main(String[] args) {
        char[][] b = new char[][]{
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };
        char[][] b2 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("原式");
        System.out.println(Util.matrixToString(b));
        P37_SudokuSolver2 sudoku = new P37_SudokuSolver2();
        sudoku.solve();
        System.out.println("结果");
        printBoard(sudoku.board);
//        System.out.println(Util.matrixToString(sudoku.board));
//        System.out.println("use: " + sudoku.use() + ", prepare time: " + sudoku.prepareTime() + ", guess count: " + sudoku.guessCount + ", back count: " + sudoku.backtrackingCount);

//        Sudoku sudoku2 = new Sudoku(b2);
//        sudoku2.solve();
//        System.out.println(Util.matrixToString(sudoku2.board));
//        System.out.println("use: " + sudoku2.use() + ", prepare time: " + sudoku2.prepareTime() + ", guess count: " + sudoku2.guessCount + ", back count: " + sudoku2.backtrackingCount);
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            StringBuilder line = new StringBuilder("[");
            line.append(valToString(board[i][0]));
            for (int j = 1; j < 9; j++) {
                line.append(", ").append(valToString(board[i][j]));
            }
            line.append("]");
            System.out.println(line);
        }
    }

    public static String valToString(int value) {
        if (value == INIT_VALUE) {
            return ".";
        }
        String s = "";
        for (int i = 1; i <= 9; i++) {
            if ((value & (1 << i)) != 0) {
                s += i;
            }
        }
        return s;
    }
}
