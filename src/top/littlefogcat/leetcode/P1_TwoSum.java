package top.littlefogcat.leetcode;

import java.util.HashMap;

public class P1_TwoSum {

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
}