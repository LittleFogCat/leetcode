package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P46_Permutations {
    //    private static final int USED = -1847158917; // 标记这个数已经在排列中了，不要再取一次
//
//    public List<List<Integer>> permute(int[] nums) {
//        ArrayList<List<Integer>> r = new ArrayList<>();
//        helper(new ArrayList<>(), nums, r);
//        return r;
//    }
//
    // 利用回溯，按位排列数字
    private void helper(List<Integer> list, Object[] nums, List<List<Integer>> r) {
        if (list.size() == nums.length) {
            List<Integer> p = new ArrayList<>(list);
            r.add(p);
            return;
        }
        // 从数组中取一个数到排列中，然后取下一个
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == USED) continue;
            int num = (int) nums[i];
            list.add(num); // 将当前循环的数添加到排列中
            nums[i] = USED;
            helper(list, nums, r); // 下一位
            list.remove(list.size() - 1); // 回溯
            nums[i] = num; // 回溯，取另外一个数
        }
    }

    private static final Object USED = new Object(); // 标记这个数已经在排列中了，不要再取一次

    public List<List<Integer>> permute(int[] nums) {
        Object[] nn = new Object[nums.length];
        for (int i = 0; i < nn.length; i++) {
            nn[i] = nums[i];
        }
        ArrayList<List<Integer>> r = new ArrayList<>();
        helper(new ArrayList<>(), nn, r);
        return r;
    }
}
