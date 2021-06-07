package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> ints = new ArrayList<>();
        for (int i = 0, min = intervals[0][0], max = intervals[0][1]; i < intervals.length; i++) {
            max = Math.max(max, intervals[i][1]);
            if (i == intervals.length - 1) {
                ints.add(new int[]{min, max});
            } else if (max < intervals[i + 1][0]) {
                ints.add(new int[]{min, max});
                min = intervals[i + 1][0];
            }
        }
        int[][] intss = new int[ints.size()][];
        for (int i = 0; i < ints.size(); i++) {
            intss[i] = ints.get(i);
        }
        return intss;
    }

}
