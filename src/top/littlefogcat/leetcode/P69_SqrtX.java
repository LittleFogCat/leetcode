package top.littlefogcat.leetcode;

public class P69_SqrtX {
    public static final int MAX = (int) Math.sqrt(Integer.MAX_VALUE);

    public int mySqrt(int x) {
        if (x == Integer.MAX_VALUE) return MAX;
        int left = 0, right = MAX;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int pow = mid * mid;
            if (pow == x || pow < x && (mid + 1) * (mid + 1) > x) {
                return mid;
            } else if (pow > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
