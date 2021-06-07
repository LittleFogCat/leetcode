package top.littlefogcat.leetcode;

public class P238_ProductOfArrayExceptSelf {
    /**
     * 因为不能使用除法，所以只使用乘法，使用数组保存前缀乘积和后缀乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] prefix = new int[len];
        prefix[0] = 1;
        for (int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            nums[i] = nums[i + 1] * nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            prefix[i] = prefix[i] * nums[i + 1];
        }
        return prefix;
    }
}
