package top.littlefogcat.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

public class P0804_PowerSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        helper(nums, 0, new int[nums.length], 0, results);
        return results;
    }

    private void helper(int[] nums, int start, int[] used, int len, List<List<Integer>> results) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            result.add(used[i]);
        }
        results.add(result);
        for (int i = start; i < nums.length; i++) {
            used[len] = nums[i]; // 添加一位
            helper(nums, i + 1, used, len + 1, results); // 递归
        }
    }

    public static void main(String[] args) {

    }
}
