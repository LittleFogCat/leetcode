package top.littlefogcat.leetcode;

import java.util.HashMap;

public class P1_TwoSum {

    /**
     * 使用Map可以把时间复杂度降到O(n)
     */
    class Solution {
        private final HashMap<Integer, Integer> map = new HashMap<>();

        public int[] twoSum(int[] nums, int target) {
            map.clear();
            for (int i = 0; i < nums.length; i++) {
                int val = nums[i];
                if (map.containsKey(val)) {
                    return new int[]{map.get(val), i};
                }
                map.put(target - val, i);
            }
            return null;
        }

    }

    /**
     * O(n^2)解法
     */
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                int tar = target - nums[i];
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == tar) return new int[]{i, j};
                }
            }
            return null;
        }
    }
}