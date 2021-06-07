package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P78_Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        if (nums.length == 0) {
            r.add(new ArrayList<>());
            return r;
        }
        expand(nums, new ArrayList<>(), r);
        return r;
    }

    public void expand(int[] nums, List<Integer> selected, List<List<Integer>> r) {
        // add current
        List<Integer> sub = new ArrayList<>();
        for (Integer pos : selected) {
            sub.add(nums[pos]);
        }
        r.add(sub);
        // expand
        int from = selected.size() == 0 ? 0 : selected.get(selected.size() - 1) + 1;
        for (int i = from; i < nums.length; i++) {
            List<Integer> selectedCopy = new ArrayList<>(selected.size());
            selectedCopy.addAll(selected);
            selectedCopy.add(i);
            expand(nums, selectedCopy, r);
        }
    }

    // -----------------------------------

    public List<List<Integer>> subsets0(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (Integer num : nums) {
            numList.add(num);
        }
        subsets(numList, r);
        r.add(new ArrayList<>());
        return r;
    }

    public void subsets(List<Integer> nums, List<List<Integer>> r) {
        if (nums.size() == 0 || contains(nums, r)) return;
        r.add(nums);
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> copy = new ArrayList<>(nums);
            copy.remove(i);
            subsets(copy, r);
        }
    }

    public boolean contains(List<Integer> nums, List<List<Integer>> r) {
        for (List<Integer> list : r) {
            if (list.equals(nums)) return true;
        }
        return false;
    }
}
