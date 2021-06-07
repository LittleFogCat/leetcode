package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class P1389_CreateTargetArrayInTheGivenOrder {
    public int[] createTargetArray(int[] nums, int[] index) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) a.add(index[i], nums[i]);
        int[] r = new int[a.size()];
        for (int i = 0; i < r.length; i++) r[i] = a.get(i);
        return r;
    }

}
