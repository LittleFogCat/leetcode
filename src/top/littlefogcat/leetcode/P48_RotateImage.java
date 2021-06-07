package top.littlefogcat.leetcode;

public class P48_RotateImage {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < (N + 1) / 2; j++) {
                // 左上角的坐标 (i, j)
                int rti = j, rtj = N - 1 - i; // 右上角坐标
                int rbi = N - 1 - i, rbj = N - 1 - j; // 右下角坐标
                int lbi = N - 1 - j, lbj = i;// 左下角坐标
                // 临时变量
                int[] v = new int[]{matrix[i][j], matrix[rti][rtj], matrix[rbi][rbj], matrix[lbi][lbj]};
                matrix[rti][rtj] = v[0]; // 左上角移到右上角
                matrix[rbi][rbj] = v[1]; // 右上角移到右下角
                matrix[lbi][lbj] = v[2]; // 右下角移到左下角
                matrix[i][j] = v[3]; // 左下角移到左上角
            }
        }
    }
}
