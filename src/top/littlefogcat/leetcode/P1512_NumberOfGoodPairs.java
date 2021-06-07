package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P1512_NumberOfGoodPairs {
    public int numIdenticalPairs0(int[] nums) {
        if (nums.length == 1) return 0;
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            numMap.put(num, numMap.containsKey(num) ? numMap.get(num) + 1 : 1);
        }
        int sum = 0;
        for (Integer value : numMap.values()) {
            if (value < 2) continue;
            sum += (value - 1) * value / 2;
        }
        return sum;
    }

    public int numIdenticalPairs(int[] nums) {
        if (nums.length == 1) return 0;
        int sum = 0, c = 1;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                sum += (c - 1) * c / 2;
                c = 1;
            } else c++;
        }
        sum += (c - 1) * c / 2;
        return sum;
    }
}
