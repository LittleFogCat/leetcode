package top.littlefogcat.leetcode;

public class P1572_MatrixDiagonalSum {
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int last = mat.length / 2;
        for (int i = 0; i < last; i++) {
            int theOther = mat.length - 1 - i;
            sum += mat[i][i]; // 左上
            sum += mat[i][theOther]; // 右上
            sum += mat[theOther][i]; // 左下
            sum += mat[theOther][theOther]; // 右下
        }
        if (mat.length % 2 == 1) {
            sum += mat[last][last];
        }
        return sum;
    }
}
