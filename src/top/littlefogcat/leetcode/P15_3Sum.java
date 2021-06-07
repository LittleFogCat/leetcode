package top.littlefogcat.leetcode;

import java.util.*;

//14
public class P15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ts = new ArrayList<>();
        if (nums == null || nums.length < 3) return ts;
        Arrays.sort(nums);

        for (int i = 0, j, k; i < nums.length - 2 && i != -1; ) {
            if (nums[i] > 0) break;
            int target = -nums[i];

            for (j = i + 1, k = nums.length - 1; j < k && j != -1 && k != -1; ) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    ts.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[++j]) ;
                    while (j < k && nums[k] == nums[--k]) ;
                } else if (sum < target) {
                    while (j < k && nums[j] == nums[++j]) ;
                } else {
                    while (j < k && nums[k] == nums[--k]) ;
                }
            }
            while (i < nums.length - 2 && nums[i] == nums[++i]) ;
        }

        return ts;
    }



}
