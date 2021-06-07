package top.littlefogcat.leetcode;

import java.util.*;

public class P37_SudokuSolver {
    public void solveSudoku(char[][] board) {
        Sudoku sudoku = new Sudoku(board);
        sudoku.solve();
    }

    public static class Sudoku {
        private Board board;
        private final List<Guess> guessChain = new ArrayList<>(); // 猜测-回溯

        private class Guess {
            private Board copy;
            private int row;
            private int col;
            private int guessNum;
        }

        public Sudoku(char[][] orig) {
            board = new Board(orig);
        }

        public void solve() {
            for (boolean changed = true; changed; ) {
                changed = false;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        changed |= board.solve(i, j); // 解(i, j)
                        if (board.board[i][j].size < 1) { // 猜测有误
                            backtrackGuess(); // 回溯猜测
                            return;
                        }
                    }
                }
            }
            if (!board.isSolved()) startGuess();
            else for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board.orig[i][j] = (char) ('0' + board.board[i][j].first());
                }
            }
        }

        private void startGuess() {
            Guess guess = new Guess();
            Value[][] board = this.board.board;
            int minSize = 0;
            int row = 0, col = 0;
            for (int i = 0; i < 9; i++) { // 找出一个未解的格子，进行猜测
                for (int j = 0; j < 9; j++) {
                    if (board[i][j].size != 1 && (minSize == 0 || board[i][j].size < minSize)) {
                        minSize = board[i][j].size;
                        row = i;
                        col = j;
                    }
                }
            }
            // 构建猜测对象
            guess.row = row;
            guess.col = col;
            guess.guessNum = board[row][col].first(); // 猜测的数
            guess.copy = this.board.copy(); // 当前棋盘副本
            guessChain.add(guess);
            board[row][col].set(guess.guessNum); // 设置猜测数字
            this.board.complete++;
            solve(); // 继续解
        }

        /**
         * 回溯猜测
         */
        private void backtrackGuess() {
            Guess guess = guessChain.get(guessChain.size() - 1); // 最后一次猜测
            board = guess.copy; // 恢复副本
            board.board[guess.row][guess.col].remove(guess.guessNum); // 删去一个错误答案
            guessChain.remove(guessChain.size() - 1);
            solve(); // 继续解
        }
    }

    public static class Board {
        private Value[][] board;
        private char[][] orig;
        private int complete = 0;

        public Board() {
        }

        public Board(char[][] orig) {
            this.orig = orig;
            board = new Value[orig.length][orig[0].length];
            for (int i = 0; i < orig.length; i++) {
                for (int j = 0; j < orig[0].length; j++) {
                    board[i][j] = new Value();
                    if (orig[i][j] == '.') {
                        board[i][j].value = Value.INIT_VALUE;
                        board[i][j].size = 9;
                    } else {
                        board[i][j].set(orig[i][j] - '0');
                    }
                    if (board[i][j].size == 1) complete++;
                }
            }
        }

        /**
         * @return 该格是否有改变
         */
        public boolean solve(int row, int col) {
            // i行j列
            Value v = board[row][col];
            if (v.size == 1) return false;
            int before = v.size;
            // ① 对于一个格子，如果在其行/列/块中有某个数，则在备选项中删除这个数；
            for (int i = 0; i < 9; i++) {
                if (col != i && board[row][i].size == 1) {
                    board[row][col].remove(board[row][i].first());
                }
                if (i != row && board[i][col].size == 1) {
                    board[row][col].remove(board[i][col].first());
                }
                int otherRow = row / 3 * 3 + i / 3;
                int otherCol = col / 3 * 3 + i % 3;
                if ((otherRow != row || otherCol != col) && board[otherRow][otherCol].size == 1) {
                    board[row][col].remove(board[otherRow][otherCol].first());
                }
            }
            if (v.size == 1) complete++;
            return before != v.size;
        }

        /**
         * @return 这个数独是否已经解决
         */
        public boolean isSolved() {
            return complete >= 81;
        }

        public Board copy() {
            Board b = new Board();
            b.orig = orig;
            b.board = new Value[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    b.board[i][j] = board[i][j].copy();
                }
            }
            return b;
        }

    }

    public static class Value {
        private static final int INIT_VALUE = 0b1111111110;
        private int value; // 10位
        private int size;

        public void set(int v) {
            value = (1 << v);
            size = 1;
        }

        public boolean contains(int v) {
            if (v < 1 || v > 9) return false;
            return ((value & (1 << v)) > 0);
        }

        public void remove(int v) {
            if (!contains(v)) return;
            value &= ~(1 << v);
            size--;
        }

        public int first() {
            for (int i = 1; i <= 9; i++) {
                if ((value & (1 << i)) != 0) return i;
            }
            return -1;
        }

        public Value copy() {
            Value copy = new Value();
            copy.size = size;
            copy.value = value;
            return copy;
        }
    }
}
