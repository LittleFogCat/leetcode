package top.littlefogcat.leetcode;

import java.util.*;

public class P47_PermutationsII {

    public List<List<Integer>> permuteUnique1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> rest = new ArrayList<>();
        for (int num : nums) rest.add(num);
        helper(rest, new ArrayList<>(), r);
        return r;
    }

    private void helper(List<Integer> rest, List<Integer> used, List<List<Integer>> r) {
        if (rest.size() == 0) {
            r.add(new ArrayList<>(used));
            return;
        }
        for (int i = 0; i < rest.size(); i++) {
            int num = rest.get(i);
            used.add(num);
            rest.remove(i);
            helper(rest, used, r);
            used.remove(used.size() - 1);
            rest.add(i, num);
            while (i < rest.size() - 1 && rest.get(i) == rest.get(i + 1)) i++;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> r = new ArrayList<>();
        Map rest = new Map();
        for (int num : nums) rest.add(num);
        helper(rest, new ArrayList<>(), r);
        return r;
    }

    private void helper(Map rest, List<Integer> used, List<List<Integer>> r) {
        if (rest.size == 0) {
            r.add(new ArrayList<>(used));
            return;
        }
        for (int i = rest.minIndex; i <= rest.maxIndex; i++) {
            if (rest.counts[i] == 0) continue;
            int num = i - 10;
            used.add(num);
            rest.removeIndex(i);
            helper(rest, used, r);
            used.remove(used.size() - 1);
            rest.addIndex(i);
        }
    }

    private static class Map {
        private int[] counts = new int[22];
        public int size;
        int minIndex = -1;
        int maxIndex = -1;

        public void add(int num) {
            addIndex(num + 10);
        }

        public void addIndex(int idx) {
            counts[idx]++;
            size++;
            if (idx < minIndex || minIndex == -1) minIndex = idx;
            if (idx > maxIndex || maxIndex == -1) maxIndex = idx;
        }

        public void remove(int num) {
            removeIndex(num + 10);
        }

        public void removeIndex(int idx) {
            counts[idx]--;
            size--;
        }
    }

}
