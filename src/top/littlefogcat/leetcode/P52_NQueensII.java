package top.littlefogcat.leetcode;

public class P52_NQueensII {
    private int solves = 0;

    public int totalNQueens(int n) {
        int[] board = new int[n]; // 棋盘，每一位代表该行棋子所占的位置
        totalNQueens(n, board, 0);
        return solves;
    }

    private void totalNQueens(int n, int[] board, int row) {
        if (row == n) { // 最后一行已经完毕
            solves++;
            return;
        }
        int[] valid = new int[board.length]; // 标记本行哪些列是可以落子的
        for (int i = 0; i < row; i++) {
            int j = board[i]; // i行j列
            valid[j] = 1;
            int diff = Math.abs(row - i);
            if (j + diff < board.length) valid[j + diff] = 1;
            if (j - diff >= 0) valid[j - diff] = 1;
        }
        // 回溯
        for (int i = 0; i < valid.length; i++) {
            if (valid[i] == 1) continue;
            board[row] = i; // 标记第i列落子
            totalNQueens(n, board, row + 1); // 下一行
        } // 回到循环开头，找到下一个落子点
    }
}
