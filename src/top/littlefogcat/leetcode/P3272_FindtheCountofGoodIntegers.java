package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P3272_FindtheCountofGoodIntegers {

    // pow[n] = 10^n
    private final int[] pow = new int[]{0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    public long countGoodIntegers(int n, int k) {
        // 生成回文数
        int m = (n + 1) / 2;
        int last = (int) Math.pow(10, m);
        Set<Long> set = new HashSet<>();
        long total = 0;
        for (int num = 0; num < last; num++) {
            // 生成 k 回文数
            long p = generatePalindromic(num, n);
            if (p == -1) continue;
            if (p % k != 0) continue;
            total += generate(p, n, set);
        }
        return total;
    }

    // 生成位数为 n，且后半截为 num 的回文数
    private long generatePalindromic(int num, int n) {
        if (num % 10 == 0) return -1; // 尾数不能为 0
        int p = num;
        long ans = 0;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            ans += ((long) (p % 10)) * pow[j];
            p /= 10;
        }
        return ans + num;
    }

    private long generate(long p, int n, Set<Long> set) {
        int[] count = new int[10]; // 统计0到9出现次数
        long tmp = p;
        while (tmp > 0) {
            count[(int) (tmp % 10)]++;
            tmp /= 10;
        }
        // 使用一个整数表示这个回文数每个数字出现的次数
        long key = 0;
        for (int i : count) {
            key *= 10;
            key += i;
        }
        if (set.contains(key)) {
            // 表示之前有一个所有数字出现次数与 p 相同的数字已经计算过了，跳过
            return 0;
        }
        set.add(key);
        // 根据排列组合，有 ans = A / B
        // 其中，A = P(n, n) - 首位为0的情况，即 (n - count[0]) * P(n - 1, n - 1)
        // B = P(count[0], count[0]) * P(count[1], count[1]) * ... * P(count[9], count[9])
        long ans = ((long) n - count[0]) * p(n - 1, n - 1);
        for (int i = 0; i < 10; i++) {
            if (count[i] > 1) ans /= p(count[i], count[i]);
        }
        return ans;
    }

    // 排列
    private int p(int a, int b) {
        int ans = 1;
        for (int i = a; i > a - b; i--) {
            ans *= i;
        }
        return ans;
    }

    public static void main(String[] args) {
        P3272_FindtheCountofGoodIntegers solution = new P3272_FindtheCountofGoodIntegers();
        check(3, 5, 27);
        check(1, 4, 2);
        check(5, 6, 2468);
        check(2, 3, 3);
        check(5, 1, 10935);
        check(7, 1, 617463);
        check(10, 1, 41457024);
    }

    private static void check(int n, int k, int expected) {
        P3272_FindtheCountofGoodIntegers solution = new P3272_FindtheCountofGoodIntegers();
        int ans = (int) solution.countGoodIntegers(n, k);
        System.out.printf("(%s, %s) = %s, expected = %s\n", n, k, ans, expected);
    }

}
