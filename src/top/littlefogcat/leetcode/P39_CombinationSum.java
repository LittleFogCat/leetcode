package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
                List<List<Integer>> nR = combinationSum(candidates, nTarget, i);
                for (List<Integer> list : nR) {
                    list.add(num);
                }
                r.addAll(nR);
            }
        }
        return r;
    }
}
