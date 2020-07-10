package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后
 */
public class P51_NQueen {
    public static void main(String[] args) {
        P51_NQueen p = new P51_NQueen();

        System.out.println(p.solveNQueens(8));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> r = new ArrayList<>();
        int[] board = new int[n];
        solveNQueens(board, 0, r);
        return r;
    }

    private void solveNQueens(int[] board, int row, List<List<String>> r) {
        if (row == board.length) {
            addToResult(board, r);
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
        for (int i = 0; i < valid.length; i++) {
            if (valid[i] == 1) continue;
            board[row] = i; // 标记第i列落子
            solveNQueens(board, row + 1, r); // 下一行
        } // 回到循环开头，找到下一个落子点
    }

    /**
     * 根据棋盘构建字符串
     */
    private void addToResult(int[] board, List<List<String>> r) {
        List<String> oneSolve = new ArrayList<>(board.length);
        for (int i = 0; i < board.length; i++) {
            int j = board[i]; // i行j列
            String s = ".".repeat(j);
            s += "Q";
            s += ".".repeat(board.length - j - 1);
            oneSolve.add(s);
        }
        r.add(oneSolve);
    }
}
