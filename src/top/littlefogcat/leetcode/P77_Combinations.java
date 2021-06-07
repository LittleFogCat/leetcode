package top.littlefogcat.leetcode;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import static top.littlefogcat.leetcode.Util.swap;

public class P77_Combinations {
    // ------------ 动态规划 ---------------
    private List[][] dp = new List[32][32];

    public List<List<Integer>> combine1(int n, int k) {
        if (dp[n][k] != null) return dp[n][k];
        List<List<Integer>> r = new ArrayList<>();
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                r.add(Collections.singletonList(i));
            }
        } else if (n == k) {
            List<Integer> oneSolution = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                oneSolution.add(i);
            }
            r.add(oneSolution);
        } else {
            List<List<Integer>> last = combine1(n - 1, k - 1); // f(n-1, k-1)
            for (List<Integer> list : last) {
                List<Integer> oneSolution = new ArrayList<>(list);
                oneSolution.add(n);
                r.add(oneSolution);
            }
            r.addAll(combine1(n - 1, k)); // f(n-1, k)
        }
        dp[n][k] = r;
        System.out.println("n: " + n + ", k: " + k + ", r: " + r);
        return r;
    }

    // ------------------- 回溯 ---------------------
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> r = new ArrayList<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        backtrack(arr, k, 0, 0, r);
        return r;
    }

    /**
     * 回溯
     */
    private void backtrack(int[] arr, int k, int used, int start, List<List<Integer>> r) {
        if (used + arr.length - start < k) return;
        if (used == k) {
            r.add(new MyList(arr, k));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, i, used);
            backtrack(arr, k, used + 1, i + 1, r);
            swap(arr, i, used);
        }
    }

    public static class MyList extends AbstractList<Integer> {
        private final int[] a;

        MyList(int[] array, int length) {
            a = Arrays.copyOf(array, length);
        }

        @Override
        public int size() {
            return a.length;
        }

        @Override
        public Integer get(int index) {
            return a[index];
        }
    }
}
