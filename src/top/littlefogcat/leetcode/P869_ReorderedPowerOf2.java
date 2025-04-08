package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P869_ReorderedPowerOf2 {
    public boolean reorderedPowerOf2(int n) {
        int[] count = countNums(n);
        for (int i = 1; i < Integer.MAX_VALUE / 2; i *= 2) {
            int[] powCount = countNums(i);
            if (Arrays.equals(count, powCount)) {
                return true;
            }
        }
        return false;
    }

    private int[] countNums(int n) {
        int[] count = new int[10];
        while (n > 0) {
            int num = n % 10;
            count[num]++;
            n /= 10;
        }
        return count;
    }

}
