package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.List;

public class P119_PascalsTriangleII {
    // 常规解法
    public List<Integer> getRow(int rowIndex) {
        Integer[] nums = new Integer[rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            nums[i] = 1;
            for (int j = i - 1; j >= 1; j--) {
                nums[j] = nums[j] + nums[j - 1];
            }
        }
        return Arrays.asList(nums);
    }

    // 找规律
    // 1  n  n(n-1)/2  n(n-1)(n-2)/6  ...
    //  *n  *(n-1)/2  *(n-2)/3  ...
    public List<Integer> getRow1(int n) {
        Integer[] nums = new Integer[n + 1]; // 一共n+1个
        nums[0] = 1;
        for (int i = 1; i < (n + 2) / 2; i++) {
            nums[i] = ((int) (((long) nums[i - 1]) * (n - i + 1) / i));
        }
        for (int i = (n + 2) / 2; i <= n; i++) {
            nums[i] = nums[n - i];
        }
        return Arrays.asList(nums);
    }
}
