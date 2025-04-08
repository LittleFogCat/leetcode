package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.*;

public class P130_SurroundedRegions {
    boolean[][] checked;

    public void solve(char[][] board) {
        if (board.length < 2 || board[0].length < 2) return;
        checked = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'X' || checked[row][col]) continue;
                bfs(board, point(row, col));
            }
        }
    }

    private int point(int row, int col) {
        return (row << 16) | col;
    }

    private int row(int point) {
        return point >> 16;
    }

    private int col(int point) {
        return point & 0xFFFF;
    }

    private void bfs(char[][] board, int p) {
        boolean shouldFillX = true;
        LinkedList<Integer> stack = new LinkedList<>();
        Set<Integer> fillList = new HashSet<>();
        stack.push(p);
        while (stack.size() > 0) {
            int top = stack.pop();
            int row = row(top);
            int col = col(top);
            if (fillList.contains(top) || checked[row][col]) continue;
            checked[row][col] = true;
            if (row == 0 || col == 0 || row == board.length - 1 || col == board[0].length - 1) {
                shouldFillX = false;
            }
            if (shouldFillX) fillList.add(top);
            if (row != 0 && board[row - 1][col] == 'O') {
                stack.push(point(row - 1, col));
            }
            if (col != 0 && board[row][col - 1] == 'O') {
                stack.push(point(row, col - 1));
            }
            if (row != board.length - 1 && board[row + 1][col] == 'O') {
                stack.push(point(row + 1, col));
            }
            if (col != board[0].length - 1 && board[row][col + 1] == 'O') {
                stack.push(point(row, col + 1));
            }
        }
        if (shouldFillX) for (Integer point : fillList) {
            int row = row(point);
            int col = col(point);
            board[row][col] = 'X';
        }
    }

    // ----------------------------------------------------

    public void solve2(char[][] board) {
        if (board.length < 2 || board[0].length < 2) return;
        for (int i = 0; i < board.length; i++) {
            mark(board, i, 0);
            mark(board, i, board[0].length - 1);
        }
        for (int i = 0; i < board[0].length; i++) {
            mark(board, 0, i);
            mark(board, board.length - 1, i);
        }
        Util.printMatrix(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'A') board[i][j] = 'O';
            }
        }
    }

    private void mark(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') return;
        board[row][col] = 'A';
        mark(board, row - 1, col);
        mark(board, row + 1, col);
        mark(board, row, col - 1);
        mark(board, row, col + 1);
    }


    public static void main(String[] args) {
        P130_SurroundedRegions p = new P130_SurroundedRegions();
        char[][] tc = new char[][]{{'X', 'O', 'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X', 'O', 'X'}};
        p.solve2(tc);
        Util.printMatrix(tc);
    }
}
