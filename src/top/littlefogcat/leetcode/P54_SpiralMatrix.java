package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P54_SpiralMatrix {
    private int row = 0, col = 0;
    private int t, r, b, l; // 遇到就该转弯了！
    private static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;
    private int direction = RIGHT;
    private final List<Integer> rs = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        t = 1;
        l = 0;
        b = matrix.length - 1;
        r = matrix[0].length - 1;
        int len = matrix.length * matrix[0].length; // 遍历次数
        for (int i = 0; i < len; i++) {
            rs.add(matrix[row][col]);
            next();
        }
        return rs;
    }

    // 下一个遍历点
    private void next() {
        switch (direction) {
            case RIGHT -> {
                if (col < r) col++;
                else {
                    row++;
                    r--;
                    direction = DOWN;
                }
            }
            case DOWN -> {
                if (row < b) row++;
                else {
                    col--;
                    b--;
                    direction = LEFT;
                }
            }
            case LEFT -> {
                if (col > l)
                    col--;
                else {
                    row--;
                    l++;
                    direction = UP;
                }
            }
            case UP -> {
                if (row > t) row--;
                else {
                    col++;
                    t++;
                    direction = RIGHT;
                }
            }
        }
    }
}
