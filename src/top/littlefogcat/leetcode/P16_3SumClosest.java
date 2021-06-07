package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P16_3SumClosest {
    /**
     * 暴力法
     */
    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum - target) < Math.abs(closest - target)) {
                        closest = sum;
                    }
                }
            }
        }
        return closest;
    }

    /**
     * 排序+双指针
     */
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum < target) j++;
                else k--;
            }
        }
        return closest;
    }

    /**
     * 终极优化版
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int min = nums[i] + nums[i + 1] + nums[i + 2];
            if (min > target) {
                closest = closest(target, closest, min);
                continue;
            }
            int max = nums[i] + nums[nums.length - 2] + nums[nums.length - 1];
            if (max < target) {
                closest = closest(target, closest, max);
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) return target;
                closest = closest(target, closest, sum);
                if (sum < target) while (j < k && nums[j] == nums[++j]) ;
                else while (j < k && nums[k] == nums[--k]) ;
            }
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) i++;
        }
        return closest;
    }

    private int closest(int target, int a, int b) {
        return Math.abs(target - a) < Math.abs(target - b) ? a : b;
    }

}
