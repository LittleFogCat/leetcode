package top.littlefogcat.leetcode;

public class P1470_ShuffleTheArray {
    public int[] shuffle0(int[] nums, int n) {
        int[] arr = new int[n * 2];
        for (int i = 0; i < n; i++) {
            arr[i * 2] = nums[i];
            arr[i * 2 + 1] = nums[i + n];
        }
        return arr;
    }


    public int[] shuffle(int[] nums, int n) {
        if (n == 1) return nums;
        for (int i = 1; i < nums.length - 1; i++) {
            int i_ = i < n ? i * 2 : (i - n) * 2 + 1; // x'
            nums[i_] = nums[i_] | (nums[i] << 16); // 高16位保存新值，低16位保存旧值
        }
        for (int i = 1; i < nums.length - 1; i++) {
            nums[i] >>= 16; // 新值替代旧值
        }
        return nums;
    }
}
