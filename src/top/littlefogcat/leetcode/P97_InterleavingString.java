package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P97_InterleavingString {

    boolean[][] r;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        r = new boolean[s1.length() + 1][s2.length() + 1];
        r[0][0] = true;
        isInterleave(s1, s2, s3, 0, 0);
        return r[s1.length()][s2.length()];
    }

    /**
     * 构建数组r
     * r[M][N]的含义：
     * r[M][N]表示s1的前M项与s2的前N项是否可以组成s3的前M+N项。
     * <p>
     * 运行这个方法的先决要件是：s1 前 M 项与 s2 前 N 项可以构成 s3 的前 M+N 项
     * <p>
     * 把二维数组 r 看做一个矩阵，整个方法的调用过程就是往最右下探索的过程。
     */
    private void isInterleave(String s1, String s2, String s3, int M, int N) {
        if (M == s1.length() && N == s2.length()) return; // 符合

        char c3 = s3.charAt(M + N);
        if (M < s1.length() && c3 == s1.charAt(M) && !r[M + 1][N]) {
            r[M + 1][N] = true;
            isInterleave(s1, s2, s3, M + 1, N);
        }
        if (N < s2.length() && c3 == s2.charAt(N) && !r[M][N + 1]) {
            r[M][N + 1] = true;
            isInterleave(s1, s2, s3, M, N + 1);
        }
    }

    public static void main(String[] args) {
        P97_InterleavingString p = new P97_InterleavingString();
        System.out.println(p.isInterleave("bc",
                "ab",
                "bbac"));
    }
}
