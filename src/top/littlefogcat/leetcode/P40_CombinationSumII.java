package top.littlefogcat.leetcode;

import java.util.*;

public class P40_CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(candidates, target, 0);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target, int start) {
        List<List<Integer>> r = new ArrayList<>();
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            if (target == num) {
                List<Integer> list = new ArrayList<>();
                list.add(num);
                r.add(list);
            }
            if (target > num) {
                int nTarget = target - num;
                List<List<Integer>> nR = combinationSum(candidates, nTarget, i + 1);
                for (List<Integer> list : nR) {
                    list.add(num);
                }
                r.addAll(nR);
            }
            while (i + 1 < candidates.length && candidates[i + 1] == candidates[i]) i++;
        }
        return r;
    }

}
