package top.littlefogcat.leetcode;

public class P674_LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;
        int max = 1, start = 0;
        for (int i = 1; ; i++) {
            if (i == nums.length || nums[i] <= nums[i - 1]) {
                max = Math.max(max, i - start);
                start = i;
                if (i == nums.length) break;
            }
        }
        return max;
    }
}
