package top.littlefogcat.leetcode;

public class P1480_RunningSumOf1dArray {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) nums[i] += nums[i - 1];
        System.gc();
        return nums;
    }
}
