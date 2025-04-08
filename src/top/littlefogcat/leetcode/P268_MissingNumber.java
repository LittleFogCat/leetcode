package top.littlefogcat.leetcode;

/**
 * 思路：0~n求和再减实际的数组和，得到的就是缺失的那个
 */
public class P268_MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (1 + n) * n / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    /**
     * 利用位运算，起到同样效果，但是可以防止溢出。
     * 数组中出现的数字都异或了2次抵消，剩下的就是没有出现的那个。
     */
    public int missingNumber1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            // i: 0~n-1
            n ^= i;
            n ^= nums[i];
        }
        return n;
    }
}
