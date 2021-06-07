package top.littlefogcat.leetcode;

public class P167_TwoSumII {
    /**
     * 双指针
     */
    public int[] twoSum(int[] numbers, int target) {
        for (int left = 0, right = numbers.length - 1; ; ) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            if (sum < target) left++;
            if (sum > target) right--;
        }
    }
}
