package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P75_SortColors {
    public void sortColors(int[] nums) {
        for (int i = 0, p0 = -1, p2 = -1; i < nums.length; i++) {
            System.out.println("i = " + i);
            if (nums[i] == 0 && p2 == -1) swap(nums, i, ++p0);
            else if (nums[i] == 1 && p2 != -1) swap(nums, i, p2++);
            else if (nums[i] == 2 && p2 == -1) p2 = i;
            else if (nums[i] == 0 && p2 != -1) {
                swap(nums, i, p2);
                swap(nums, ++p0, p2++);
            }
//            System.out.println(Arrays.toString(nums) + " " + p0 + " " + p2);
        }
    }

    private void swap(int[] nums, int p, int q) {
        int t = nums[p];
        nums[p] = nums[q];
        nums[q] = t;
//        System.out.println("swap: " + Arrays.toString(nums));
    }
}
