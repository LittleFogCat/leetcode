package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P54_SpiralMatrix1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> rs = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return rs;
        spiralOrder(matrix, 0, matrix[0].length - 1, matrix.length - 1, 0, rs);
        return rs;
    }

    public void spiralOrder(int[][] matrix, int t, int r, int b, int l, List<Integer> rs) {
        if (t > b || l > r) return; // 遍历结束
        if (t == b) for (int i = l; i <= r; i++) rs.add(matrix[t][i]); // 只剩一行
        else if (l == r) for (int i = t; i <= b; i++) rs.add(matrix[i][l]); // 只剩一列
        else {
            for (int i = l; i < r; i++) rs.add(matrix[t][i]); // 上
            for (int i = t; i < b; i++) rs.add(matrix[i][r]); // 右
            for (int i = r; i > l; i--) rs.add(matrix[b][i]); // 下
            for (int i = b; i > t; i--) rs.add(matrix[i][l]); // 左
            spiralOrder(matrix, t + 1, r - 1, b - 1, l + 1, rs);
        }
    }

}
