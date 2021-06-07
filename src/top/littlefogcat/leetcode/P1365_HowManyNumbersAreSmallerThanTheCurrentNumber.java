package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P1365_HowManyNumbersAreSmallerThanTheCurrentNumber {
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int[] r = new int[nums.length];
        for (int i = 0; i < r.length; i++) {
            int count = 0;
            for (int num : nums) if (num < nums[i]) count++;
            r[i] = count;
        }
        return r;
    }

    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int[] r = new int[nums.length];
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < r.length; i++) {
            if (numCount.containsKey(nums[i])) r[i] = numCount.get(nums[i]);
            else {
                int count = 0;
                for (int num : nums) if (num < nums[i]) count++;
                r[i] = count;
                numCount.put(nums[i], count);
            }
        }
        return r;
    }

    public int[] smallerNumbersThanCurrent3(int[] nums) {
        int[] r = new int[nums.length];
        System.arraycopy(nums, 0, r, 0, nums.length);
        Arrays.sort(nums);
        Map<Integer, Integer> numsMap = new HashMap<>();
        numsMap.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            numsMap.put(nums[i], i);
        }
        for (int i = 0; i < r.length; i++) {
            r[i] = numsMap.get(r[i]);
        }
        return r;
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] map = new int[101];
        for (int num : nums) {
            map[num]++;
        }
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                int old = count;
                count += map[i];
                map[i] = old;
            }
        }
        int[] r = new int[nums.length];
        for (int i = 0; i < r.length; i++) {
            r[i] = map[nums[i]];
        }
        return r;
    }
}
