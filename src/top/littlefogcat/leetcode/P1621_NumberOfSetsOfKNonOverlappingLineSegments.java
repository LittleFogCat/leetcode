package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P1621_NumberOfSetsOfKNonOverlappingLineSegments {
    private static int MOD = (int) (Math.pow(10, 9) + 7);

    public int numberOfSets2(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        numberOfSets2(n, k, dp);
        Util.printMatrix(dp);
        return dp[n][k];
    }

    private int numberOfSets2(int n, int k, int[][] dp) {
        if (dp[n][k] == 0) {
            if (k == 0) dp[n][k] = 1;
            else {
                for (int start = 0; start < n - k; start++) {
                    for (int end = start + 1; end <= n - k; end++) {
                        dp[n][k] += numberOfSets2(n - end, k - 1, dp);
                        if (dp[n][k] > MOD) dp[n][k] %= MOD;
                    }
                }
            }
        }
//        System.out.println(n + "个点, " + k + "个线段: " + dp[n][k]);
        return dp[n][k];
    }

    public int numberOfSets1(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        numberOfSets(n, k, dp);
        Util.printMatrix(dp);
        return dp[n][k];
    }

    private int numberOfSets(int n, int k, int[][] dp) {
        if (dp[n][k] == 0) {
            if (k == n - 1 || k == 0) dp[n][k] = 1;
            else {
                for (int start = 0; start < n - k; start++) {
                    for (int end = start + 1; end <= n - k; end++) {
                        dp[n][k] += numberOfSets(n - end, k - 1, dp);
                        if (dp[n][k] > MOD) dp[n][k] %= MOD;
                    }
                }
            }
        }
//        System.out.println(n + "个点, " + k + "个线段: " + dp[n][k]);
        return dp[n][k];
    }

    /*
     * dp[n][k] = for (int start = 0; start < n - k; start++) {
     *                 for (int end = start + 1; end <= n - k; end++) {
     *                     dp[n][k] += dp[n - end][k - 1]
     *                     dp[n][k] %= MOD;
     *                 }
     *             }
     * 消去变量k：
     * dp' [n]   = for (int start = 0; start < n - k; start++) {
     *                 for (int end = start + 1; end <= n - k; end++) {
     *                     dp'[n] += dp[n - end];
     *                     dp'[n] %= MOD;
     *                 }
     *             }
     * 可见，只与小于n的相关，故从大到小递推
     */

    /**
     * @param N 点数
     * @param K 线段数
     */
    public int numberOfSets3(int N, int K) {
        int MOD = 1000000007;
        long[] dp = new long[N + 1];
        Arrays.fill(dp, 1); // k == 0 的情况
        for (int k = 1; k <= K; k++) {
            for (int n = N; n > k + 1; n--) {
                dp[n] = 0;
                for (int end = 1; end <= n - k; end++) {
                    dp[n] += dp[n - end] * end;
                    if (dp[n] >= MOD) dp[n] %= MOD;
                }
            }
            dp[k + 1] = 1;
        }
        return (int) dp[N];
    }

    public int numberOfSets4(int N, int K) {
        int MOD = 1000000007;
        long r = 0;
        int min = K + 1, max = Math.min(K * 2, N); // K个线段，最少需要K+1个点，最多2K
        long[][] dp = new long[N][K];
        int[] C = C(N, MOD);
        for (int i = min; i <= max; i++) {
            long chooseI = C[i]; // C(N, i) 组合数
            // 现在有i个点，要构成k个线段，有多少种方法？
            int b = i - min; // 需要的断点数 breaks to choose
            int t = i - 3; // 可供断开的间隔 total breaks
            long chooseK = calBreaks(t, b, dp, MOD);
            // System.out.println("C(" + N + ", " + i + ") = " + chooseI + ", chooseK = " + chooseK);
            r = (r + (chooseI % MOD) * (chooseK % MOD)) % MOD;
        }
        return (int) r;
    }

    /**
     * 在t个点中选择b个点，且两点之间互不相邻
     */
    private long calBreaks(int t, int b, long[][] dp, int p) {
        if (b > (t + 1) / 2) return 0; // 最多可以找出(t+1)/2个断点
        if (b == 0) return 1; // 边界条件
        if (b == 1) return t;
        if (dp[t][b] != 0) return dp[t][b];
        for (int i = 0; i < t; i++) {
            // 选择第i个
            dp[t][b] += calBreaks(t - i - 2, b - 1, dp, p);
            if (dp[t][b] > p) dp[t][b] %= p;
        }
        return dp[t][b];
    }

    /**
     * 利用杨辉三角求组合数
     *
     * @return C(n, 0) ~ C(n,n)
     */
    private int[] C(int n, int p) {
        int[] r = new int[n + 1]; // 从0开始，第n行有n+1个数
        r[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) { // 计算C(i, j)
                r[j] = r[j] + r[j - 1];
                if (r[j] > p) r[j] %= p;
            }
        }
        return r;
    }


    public int numberOfSets6(int n, int k) {
        int[] dp = new int[n];
        int[] sums = new int[n];
        int p = 1000000007;
        dp[0] = sums[0] = 1;
        for (int i = 0; i <= k; i++) { // i个线段
            for (int j = 0; j < n; j++) {
                if (j < i) dp[j] = 0;
                else if (j == i || i == 0) dp[j] = 1;
                else dp[j] = (sums[j - 1] + dp[j - 1]) % p;
                // i个线段、j个点
            }
            sums[0] = dp[0];
            for (int j = 1; j < n; j++) {
                sums[j] = (dp[j] + sums[j - 1]) % p;
                // sums[j] = sum( dp[0 ~ j] )
            }
            System.out.println(Arrays.toString(dp));
//            System.out.println(Arrays.toString(sums));
        }
        return dp[n - 1];
    }

    public int numberOfSetsX(int n, int k) {
        int[][] dp = new int[n][k + 1];
        int p = 1000000007;
        for (int j = 0; j <= k; j++) {
            for (int i = 0; i < n; i++) {
                if (j == i || j == 0) dp[i][j] = 1; // i个缝，0个或i个线段，都是1种取法
                else if (j > i) dp[i][j] = 0; // 线段数>缝数，0种取法
                else {
                    int maxLen = i - j + 1;
                    for (int len = 1; len <= maxLen; len++) {
                        dp[i][j] += dp[i - len][j - 1] * len;
                        if (dp[i][j] > p) dp[i][j] %= p;
                    }
                }
            }
        }
//        Util.printMatrix(dp);
        return dp[n - 1][k];
    }

    public int numberOfSets(int n, int k) {
        return (int) C_Fermat(n + k - 1, k * 2, 1000000007); // 余数
    }

    // 用杨辉三角求C(n, m)
    private long C(int n, int m, int p) {
        long[] r = new long[m + 1]; // 从0开始，第n行有n+1个数
        r[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) { // 计算C(i, j)
                r[j] = r[j] + r[j - 1];
                if (r[j] > p) r[j] %= p;
            }
        }
        return r[m];
    }

    // 利用费马小定理求C(n, m)
    private long C_Fermat(int n, int m, int p) {
        // C(n, m) = a / b
        long a = multiMod(n, n - m + 1, p);
        long b = multiMod(m, 1, p);
        // a / b = a * b^(p-2) [mod p]
//        System.out.println("a = " + a + ", b = " + b);
        return a * powMod(b, p - 2, p) % p;
    }

    // = n * (n-1) * (n-2) * ... * m
    private long multiMod(int n, int m, int p) {
        long r = 1;
        for (int i = m; i <= n; i++) {
            r = r * i;
            if (r > p) r %= p;
        }
//        System.out.println(n + " * " + (n - 1) + " * ... * " + m + " = " + r);
        return r;
    }

    // 快速幂算法
    // a^(2x) = (a^2)^x
    private long powMod(long a, int pow, int p) { // a^pow % p 快速幂算法
//        System.out.print(a + " ^ " + pow + " % " + p + " = ");
        long r = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) { // a^(2x+1) = a*a^(2x)
                r = (r * a) % p;
            }
            a = a * a % p;
            pow >>= 1;
        }
//        System.out.println(r);
        return r;
    }

    public int numberOfSetsTest(int n, int k) {
        long r = 0;
        int p = 1000000007;
        for (int i = 0; i < k; i++) {
            r = (r + C_Fermat(n, 2 * k - i, p) * C_Fermat(k - 1, i, p)) % p;
        }
        return (int) r; // 余数
    }

    public static void main(String[] args) {
        P1621_NumberOfSetsOfKNonOverlappingLineSegments p = new P1621_NumberOfSetsOfKNonOverlappingLineSegments();
//        System.out.println(p.numberOfSets(4, 3));//1
//        System.out.println(p.numberOfSets(5, 3));//7
//        System.out.println(p.numberOfSets(6, 3));//28=7*4
//        System.out.println(p.numberOfSets(7, 3));//84=7*12=7*4*3
//        System.out.println(p.numberOfSets(8, 3));//210=7*30=7*5*3
//        System.out.println(p.numberOfSets(9, 3));//462 = 7 * 66=7*6*11
//        System.out.println(p.numberOfSets(10, 3));//924 = 7*66*2=7*11*12
//        System.out.println("--------------------");
//        System.out.println(p.numberOfSets(7, 0));//84=7*12=7*4*3
//        System.out.println(p.numberOfSets(7, 1));//84=7*12=7*4*3
//        System.out.println(p.numberOfSets(7, 2));//84=7*12=7*4*3
//        System.out.println(p.numberOfSets(7, 3));//84=7*12=7*4*3
//        System.out.println(p.numberOfSets(7, 4));//84=7*12=7*4*3
//        System.out.println(p.numberOfSets(7, 5));//84=7*12=7*4*3
//        System.out.println(p.numberOfSets(7, 6));//84=7*12=7*4*3
//        int r = p.numberOfSets2(30, 15);
//        System.out.println(r);
//        for (int i = Integer.MAX_VALUE; i % 11111111 != 0; i++) {
//            System.out.println(i);
//        }
//        System.out.println(p.C(4, 2));
//        System.out.println(p.C(12, 3));
//        System.out.println(p.C(15, 7));
        Util.assertValue(11628, p.numberOfSetsTest(13, 7));
//        System.out.println("正确：" + 11628);
//        System.out.println("实际：" + p.numberOfSetsTest(13, 7));
//        System.out.println("5：" + p.numberOfSets5(13, 7));
        Util.assertValue(497827474, p.numberOfSetsTest(130, 70));
//        System.out.println("正确：" + 497827474);
//        System.out.println("实际：" + p.numberOfSetsTest(130, 70));
//        System.out.println("5：" + p.numberOfSets5(130, 7));
    }
}
