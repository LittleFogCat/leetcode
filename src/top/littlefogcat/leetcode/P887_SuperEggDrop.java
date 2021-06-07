package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * <p>
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * <p>
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * <p>
 * 你的目标是确切地知道 F 的值是多少。
 * <p>
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 * <p>
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：K = 3, N = 14
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= 100
 * 1 <= N <= 10000
 */
public class P887_SuperEggDrop {

    // ------------------------- 1. 动态规划 -------------------------------

    int[][] states = null; // 保存结果，K层楼，N个蛋，需要states[K-1][N-1]次

    /**
     * 对于扔鸡蛋的楼层 = X，有
     * f(K, N) = 1 + max(f(K - 1, X), f(K, N - X))
     * <p>
     * 解：若碎了，那么剩余鸡蛋K-1，剩余楼层X；没碎，剩余鸡蛋K，剩余楼层N-X
     */
    public int superEggDrop(int K, int N) {
        states = new int[K][N]; // 初始化状态数组
        states[0][0] = 1; // 1楼，1个蛋，1次
        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                makeStateArray(k, n);
            }
        }
        printStates();
        return state(K, N);
    }

    /**
     * 换底公式求log2(N)
     */
    double log2N(double N) {
        return Math.log(N) / Math.log(2);
    }

    /**
     * 从0开始计算状态数组states[K-1][N-1]
     */
    private void makeStateArray(int K, int N) {
        if (K <= 0 || N <= 0) return; // 没有剩余了，不用扔
        if (K == 1) { // 如果只剩一个鸡蛋，只能一层层的扔
            setState(K, N, N);
        } else if (N == 1) { // 只剩一层了，那么只用扔一次
            setState(K, N, 1);
        } else {
            if (K >= log2N(N) + 1) {
                setState(K, N, (int) log2N(N) + 1);
            } else {
                int min = -1;
                for (int X = 1; X <= N; X++) { // X = i
                    int state; // 扔在X层的最坏情况
                    if (X == 1) { // 最坏情况是没有碎
                        state = 1 + state(K, N - 1);
                    } else if (X == N) { // 最坏情况是碎了
                        state = 1 + state(K - 1, N - 1);
                    } else {
                        state = 1 + Math.max(state(K - 1, X - 1) /* X层碎了 */, state(K, N - X) /* X层没有碎 */);
                    }
                    if (min == -1 || state < min) min = state;
                }
                setState(K, N, min);
            }
        }
    }

    /**
     * K个蛋，N层楼次数
     */
    private int state(int K, int N) {
        return states[K - 1][N - 1];
    }

    /**
     * K个蛋，N层楼次数为val
     */
    private void setState(int K, int N, int val) {
        states[K - 1][N - 1] = val;
    }

    private void printStates() {
        for (int k = 0; k < states.length; k++) {
            System.out.print("K=" + (k + 1) + ": ");
            int[] row = states[k];
            int current = 1;
            int count = 0;
            for (int i = 0; i < row.length; i++) {
                if (row[i] == 0 || i == row.length - 1) {
                    System.out.print(current + "[" + count + "]");
                    break;
                } else if (row[i] == current) {
                    count++;
                } else {
                    System.out.print(current + "[" + count + "], ");
                    current++;
                    count = 1;
                }
            }
            System.out.println();
        }
//        for (int n = 1; n <= states[0].length; n++) {
//            System.out.print("N = " + n + ": ");
//            for (int k = 1; k <= states.length; k++) {
//                System.out.print(states[k - 1][n - 1] + ", ");
//            }
//            System.out.println();
//        }
    }

    // ------------------------- 2. 动态规划2 -------------------------------

    /**
     * X层扔下，状态转移方程：
     * f(K, N) = 1 + max( f(K - 1, X - 1), f(K, N - X) )
     * <p>
     * 总的状态转移方程：
     * f(K, N) = 1 + min( max(f(K - 1, X - 1), f(K, N - X)) )，其中1 <= X <= N
     * <p>
     * 由此得到以下解法
     */
    public int superEggDrop2(int K, int N) {
        return dp(K, N);
    }

    private int dp(int K, int N) {
        int r = 0;
        for (int X = 1; X <= N; X++) {
            int max = Math.max(dp(K - 1, X - 1), dp(K, N - X));
            r = r == 0 || r > max ? max : r;
        }
        return r;
    }

    // ------------------------- 动态规划2.1 -------------------------------

    /**
     * 动态规划2优化，使用数组保存之前运算结果
     */
    public int superEggDrop21(int K, int N) {
        kn = new int[K + 1][N + 1];
        return dp01(K, N);
    }

    int[][] kn;

    private int dp01(int K, int N) {
        if (kn[K][N] != 0) {
            return kn[K][N];
        }
        int r = 0;
        if (N <= 1 || K == 1) r = N;
        else for (int X = 1; X <= N; X++) {
            int max = 1 + Math.max(dp01(K - 1, X - 1), dp01(K, N - X));
            r = r == 0 || r > max ? max : r;
        }
        kn[K][N] = r;
        return r;
    }

    // ------------------------- 动态规划2.2 -------------------------------

    /**
     * X层扔下，状态转移方程：
     * f(K, N) = 1 + max( f(K - 1, X - 1), f(K, N - X) )
     * <p>
     * 总的状态转移方程：
     * f(K, N) = 1 + min( max(f(K - 1, X - 1), f(K, N - X)) )，其中1 <= X <= N
     * <p>
     * 此解法为线性规划2.1的优化版，使用二分法，时间复杂度降到O(K * N * logN)
     */
    public int superEggDrop22(int K, int N) {
        return dp02(K, N);
    }

    private final Map<Integer, Integer> map = new HashMap<>();

    private int dp02(int K, int N) {
        int key = N * 100 + K;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int min;
        if (N <= 1 || K == 1) min = N;
        else { // 二分法，替代dp01中的for循环
            int left = 1;
            int right = N;
            while (right - left > 1) {
                int X = (left + right) / 2;
                // f(K,X)单调递增，f(K,N-X)单调递减，所以如果前者较小，
                // 那么X的取值应增大，反之X应减小
                if (dp02(K - 1, X - 1) < dp02(K, N - X)) left = X;
                else right = X;
            }

            min = 1 + Math.min(Math.max(dp02(K - 1, left - 1), dp02(K, N - left)), // 扔在left
                    Math.max(dp02(K - 1, right - 1), dp02(K, N - right))); // 扔在right
        }
        map.put(key, min);
        return min;
    }

    // ------------------------- 2.3 动态规划之决策单调性 -------------------------------

    /**
     * 再次观察状态转移方程：
     * f(K, N) = 1 + min( max(f(K - 1, X - 1), f(K, N - X)) )，其中1 <= X <= N
     */
    public int superEggDrop23(int K, int N) {
        return dp23(K, N);
    }

    private int dp23(int K, int N) {
        int[] dp = new int[N + 1]; // 保存K
        int[] dp1 = new int[N + 1]; // 保存K-1
        for (int i = 1; i <= N; i++) {
            dp1[i] = i; // K=1时的情况。
        }

        for (int k = 2; k <= K; k++) {
            int x0 = 1; // X指针
            for (int n = 1; n <= N; n++) {
                // 现在要求dp[n-x]
                // dp[n] = 1 + min( max(dp1[x - 1] + dp[n - x]) )
                // 而这个min的x取值就是两条函数的交点。
                // 函数的上端是先递减、后递增的；所以当下一项大于这一项时，就是最小值了。
                // 当找到这个x = x0，那么就有f(k, n) = 1 + max( f(k - 1, x0 - 1), f(k, n - x0) )
                // 即：dp[n] = 1 + max(dp1[x0 - 1], dp[n - x0]) **重要
                // 现在的任务就是找到x0，所有问题就迎刃而解了。

                // 换个说法，当K、N确定时，记函数 g(x) = max(f(K - 1, X - 1), f(K, N - X))
                // 则 f(K, N) = 1 + min(g(x))
                // 那么 g(x) 对于 x 是先递减再递增的，所以从1开始遍历g(x)的值，
                // 当 g(x) <= g(x + 1) 时停止，这个时候的x值就满足 min(g(x)) 了。

                // 另外，当N增加时，两个函数的交点就会向右靠：这意味着，当N增大时，x0也会相应的增大，
                // 至少不会变小。（因为是离散函数，交点在两个整数之间增加的话，x0会维持原值）
                while (x0 < n && Math.max(dp1[x0 - 1], dp[n - x0]) > Math.max(dp1[x0], dp[n - x0 - 1]) /* x1=x0+1 */)
                    // 因为对于n0 < n，dp[n0]之前已经求出来了，所以直接取值就行
                    x0++; // 如果当前项大于下一项，那么右移一项接着判断

                dp[n] = 1 + Math.max(dp1[x0 - 1], dp[n - x0]); // 找到x0
            }
//            System.out.println(Arrays.toString(dp) + ", " + Arrays.toString(dp1));
            // K = k 已经完毕，那么就把当前dp赋值给dp1，进行下一次循环
            int[] tmp = dp;
            dp = dp1;
            dp1 = tmp;
        }
        return dp1[N];
    }


    // ------------------------- 3.1 观察法 -------------------------------

    /**
     * 投机取巧偷鸡摸狗的方法。
     * <p>
     * 观察解法1中的states数组：
     * <p>
     * <p>
     * 对于不同的K值，统计数字重复次数：
     * <p>
     * 通过观察很容易发现，对于正整数i，它在K = k的情况下，重复次数r(i, k)为：
     * r(i, k) = r(i-1, k) + r(i-1, k-1)
     */
    public int superEggDrop31(int K, int N) {
        if (K == 1) return N; // 一个鸡蛋只能挨个扔
        if (N == 1) return 1; // 一层楼只用扔一次
        int old; // repeat[K - 1][n]
        int[] r = new int[N]; // repeat[K]
        Arrays.fill(r, 1); // K == 1，所有都是1
        for (int k = 2; k <= K; k++) {
            r[0] = 1; // 第一项为1
            old = 1; // 保存r[k]
            int total = 1; // 到i=n总共有多少项
            for (int n = 1; n < r.length && total < N; n++) { // real n = n+1
                // r(i, k) = r(i-1, k) + r(i-1, k-1)
                int oldTemp = r[n];
                r[n] = old + r[n - 1];
                total += r[n];
                if (total >= N && k == K) return n + 1; // k = K，n+1 = N
                old = oldTemp;
            }
            System.out.println("K = " + k + ", r = " + Arrays.toString(r));
        }
        return 0;
    }

    // ------------------------- 3.2 观察法2 -------------------------------

    public int superEggDrop32(int K, int N) {
        if (N == 1) return 1; // N = 1，只用扔一次
        int[][] kn = new int[K][N];
        int sum = 0;
        for (int i = 1; ; i++) {
            genKN(K, i, kn);
            sum += kn[K - 1][i - 1];
            if (sum >= N) return i;
        }
    }

    private void genKN(int k, int n, int[][] kn) {
        if (kn[k - 1][n - 1] != 0) return;
        if (k == 1 || n == 1) kn[k - 1][n - 1] = 1;
        else {
            genKN(k, n - 1, kn);
            genKN(k - 1, n - 1, kn);
            kn[k - 1][n - 1] = kn[k - 1][n - 2] + kn[k - 2][n - 2];
        }
    }


    // --------------------- 4. 逆向思维法 ------------------------

    /**
     * 逆向思维：对于K个鸡蛋，通过T次操作，最高能对应的楼层是多少？记其为 f(K, T)
     * <p>
     * 那么，类似方法1，可以得到状态转移方程：f(K, T) = 1 + f(K - 1, T - 1) + f(K, T - 1)
     * <p>
     * // （ps：我惊讶的发现，这个公式跟观察法里面求 r(i, k) 的公式惊人的相似！）
     * <p>
     * 这个方程是怎么得到的呢？假设在某层楼丢了一个鸡蛋，那么还剩余 T - 1 次操作；
     * 如果鸡蛋碎了，那么还剩 K - 1 个鸡蛋，也就是说，在当前这层楼之上，最多有 f(K - 1, T - 1) 层；
     * 反之还剩 K 个鸡蛋，也即是在当前楼层之下，最多有 f(K, T - 1) 层；
     * 所以最多的楼层是以上二者之和：1 + f(K - 1, T - 1) + f(K, T - 1)。
     * <p>
     * 特别地，当 T = 1 时，显然有 f(K, 1) = 1。
     * <p>
     * 我们只需要找到，满足 f(K, T) >= N 的最小 T 即可。
     */
    public int superEggDrop4(int K, int N) {
        int[][] f = new int[K + 1][N + 1];

        for (int t = 1; t <= N; t++) {
            for (int k = 1; k <= K; k++) {
                f[k][t] = 1 + f[k - 1][t - 1] + f[k][t - 1];
                if (f[k][t] >= N) return t;
            }
        }
        return 0;
    }

    public int superEggDrop41(int K, int N) {
        int[] f1 = new int[K + 1]; // t = t - 1

        for (int t = 1; t <= N; t++) {
            int[] f = new int[K + 1]; // t = t
            for (int k = 1; k <= K; k++) {
                f[k] = 1 + f1[k - 1] + f1[k];
                if (f[k] >= N) return t;
            }
            f1 = f;
        }
        return 0;
    }


    public static void main(String[] args) {
        P887_SuperEggDrop p = new P887_SuperEggDrop();

        System.out.println(p.superEggDrop23(2, 6));
    }
}
