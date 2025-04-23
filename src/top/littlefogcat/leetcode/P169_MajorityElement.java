package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P169_MajorityElement {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            if (!numMap.containsKey(num)) {
                numMap.put(num, 1);
            } else {
                int times = numMap.get(num);
                if (times >= len / 2) {
                    return num;
                } else {
                    numMap.put(num, times + 1);
                }
            }
        }
        return nums[0];
    }

    class Solution {
        public int majorityElement(int[] nums) {
            int candidate = nums[0];
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == candidate) {
                    count++;
                } else if (count == 1) {
                    candidate = nums[i];
                } else {
                    count--;
                }
            }
            return candidate;
        }
    }
}
