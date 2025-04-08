package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.*;

public class P90_SubsetsII {
    // ------------------------- 计数 ------------------------
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        if (nums.length == 0) {
            r.add(new ArrayList<>());
            return r;
        } else if (nums.length == 1) {
            r.add(new ArrayList<>());
            r.add(Collections.singletonList(nums[0]));
            return r;
        }
        Map<Integer, Integer> numsCountMap = new HashMap<>();
        for (int num : nums) {
            numsCountMap.put(num, numsCountMap.getOrDefault(num, 0) + 1);
        }
        int[] map = new int[numsCountMap.size()]; // keys
        int[] counts = new int[map.length]; // values
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : numsCountMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            map[i] = key;
            counts[i++] = val;
        }
        subsetsWithDup(map, counts, r, 0);
        return r;
    }

    private void subsetsWithDup(int[] map, int[] counts, List<List<Integer>> r, int current) {
        if (current >= map.length) {
            System.out.println(Arrays.toString(counts));
            List<Integer> one = new ArrayList<>();
            for (int i = 0; i < map.length; i++) {
                one.addAll(Collections.nCopies(counts[i], map[i]));
            }
            r.add(one);
            return;
        }
        int orig = counts[current];
        for (int n = 0; n <= orig; n++) {
            counts[current] = n;
            subsetsWithDup(map, counts, r, current + 1);
        }
        counts[current] = orig;
    }

    // ------------回溯-------------
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        List<List<Integer>> r = new ArrayList<>();
        System.out.println(Arrays.toString(nums));
        subsetsWithDup(nums, used, 0, r, 0);
        return r;
    }

    public void subsetsWithDup(int[] nums, int[] used, int usedCount, List<List<Integer>> r, int start) {
        List<Integer> one = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) one.add(nums[i]);
        }
        System.out.println(Arrays.toString(used));
        r.add(one);
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0 || used[i] == 1) continue;
            used[i] = 1;
            subsetsWithDup(nums, used, usedCount + 1, r, i + 1);
            used[i] = 0;
        }
    }

    public static void main(String[] args) {
        P90_SubsetsII p = new P90_SubsetsII();
        System.out.println(p.subsetsWithDup(Util.arr(4, 4, 4, 1, 4)));
    }
}
