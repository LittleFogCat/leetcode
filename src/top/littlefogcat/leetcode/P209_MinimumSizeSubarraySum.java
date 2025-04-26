package top.littlefogcat.leetcode;

public class P209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int p = 0, q = 0, sum = nums[0], len = 1; ; len = q - p + 1) {
            if (sum < target) {
                if (q == nums.length - 1) break;
                else sum += nums[++q];
            } else {
                if (len == 1) return 1;
                if (len < min) min = len;
                sum -= nums[p++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
