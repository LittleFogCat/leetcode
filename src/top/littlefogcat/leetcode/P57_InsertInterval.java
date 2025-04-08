package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P57_InsertInterval {
    /**
     * 将区间分为两类：合并的、不合并的；
     * 有重叠部分的则合并，没有重叠部分则不合并。然后复制到新数组中。
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int size = 0;
        int[][] newIntervals = new int[intervals.length + 1][];
        boolean added = false;
        for (int[] interval : intervals) {
            if (interval[1] >= newInterval[0] && interval[0] <= newInterval[1]) {
                // 当前区间和newInterval有重叠部分，则将其合并到newInterval中
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            } else {
                // 不重叠
                if (!added && interval[0] > newInterval[1]) {
                    // newInterval右边的第一个区间，先添加newInterval
                    newIntervals[size++] = newInterval; // 添加合并后区间
                    added = true; // 标记已经添加
                }
                newIntervals[size++] = interval; // 不重叠直接添加
            }
        }
        if (!added) newIntervals[size++] = newInterval;
//        System.out.println(Arrays.toString(newInterval));
//        System.out.println(Arrays.deepToString(newIntervals));
        if (size == newIntervals.length) return newIntervals;
        int[][] r = new int[size][];
        System.arraycopy(newIntervals, 0, r, 0, size);
        return r;
    }
}
