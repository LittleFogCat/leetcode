package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * O(n)思路：使用Map，遍历数组时标记已经存在的数字和其相邻的数字；当遍历到被标记了相邻的数字时，进行合并。
 */
public class P128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length > 20000) {
            System.out.println("long array");
            return longestConsecutive0(nums);
        }
        Arrays.sort(nums);
        int length = 0;
        int longest = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            } else if (nums[i] == nums[i - 1] + 1) {
                if (length == 0) {
                    length = 2;
                } else {
                    length++;
                }
            } else {
                if (length != 0) {
                    length = 0;
                }
            }
            if (length > longest) {
                longest = length;
            }
        }
        return longest;
    }

    /**
     * O(n)
     * 标记连续序列的起点和终点
     */
    public int longestConsecutive0(int[] nums) {
        int flag = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, num);
                if (!map.containsKey(num - 1)) {
                    map.put(num - 1, flag);
                }
                if (!map.containsKey(num + 1)) {
                    map.put(num + 1, flag);
                }
            } else {
                int value = map.get(num);
                if (value != flag) { // 重复数字
                    continue;
                }
                boolean needExpandLeft = map.containsKey(num - 1) && map.get(num - 1) != flag;
                boolean needExpandRight = map.containsKey(num + 1) && map.get(num + 1) != flag;
                if (needExpandLeft && needExpandRight) {
                    // 连接左右
                    int start = map.get(num - 1);
                    int end = map.get(num + 1);
                    map.put(start, end);
                    map.put(end, start);
                    map.put(num, num);
                } else if (needExpandLeft) {
                    int start = map.get(num - 1);
                    map.put(start, num);
                    map.put(num, start);
                    map.put(num + 1, flag);
                } else if (needExpandRight) {
                    int end = map.get(num + 1);
                    map.put(end, num);
                    map.put(num, end);
                    map.put(num - 1, flag);
                }
            }
//            System.out.println(map);
        }
        int longest = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int start = e.getKey();
            int end = e.getValue();
            if (start == flag || end == flag) continue;
            int length = Math.abs(end - start + 1);
            if (length > longest) longest = length;
        }
        return longest;
    }
}
