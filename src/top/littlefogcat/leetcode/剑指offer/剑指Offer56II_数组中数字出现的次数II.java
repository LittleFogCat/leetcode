package top.littlefogcat.leetcode.剑指offer;

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer56II_数组中数字出现的次数II {
    public int singleNumber1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        for (Map.Entry<Integer, Integer> e : map.entrySet()) if (e.getValue() == 1) return e.getKey();
        return 0;
    }

}
