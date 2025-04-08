package top.littlefogcat.leetcode;

public class P3375_MinimumOperationstoMakeArrayValuesEqualtoK {
    int[] exists = new int[101];

    public int minOperations(int[] nums, int k) {
        if (nums.length == 0) return -1;
        int min = nums[0];
        int differentNums = 0;
        for (int num : nums) {
            if (exists[num] != 0) continue;
            differentNums++;
            exists[num] = 1;
            if (num < min) min = num;
        }
        if (k > min) return -1;
        return k == min ? differentNums - 1 : differentNums;
    }
}
