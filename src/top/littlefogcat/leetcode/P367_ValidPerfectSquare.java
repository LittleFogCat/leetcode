package top.littlefogcat.leetcode;

/**
 * 思路：二分法
 */
public class P367_ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l <= r) {
            int mid = l + (r - l) / 2; // 防溢出
            int div = num / mid; // 商
            if (div < mid) {
                r = mid - 1;
            } else if (div > mid) {
                l = mid + 1;
            } else {
                return num == div * mid; // 整除
            }
        }
        return false;
    }
}
