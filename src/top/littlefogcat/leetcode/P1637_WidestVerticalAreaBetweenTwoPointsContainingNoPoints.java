package top.littlefogcat.leetcode;

import java.util.*;

public class P1637_WidestVerticalAreaBetweenTwoPointsContainingNoPoints {
    public int maxWidthOfVerticalArea(int[][] points) {
        int[] x = new int[points.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = points[i][0];
        }
        Arrays.sort(x);
        int maxDiff = 0;
        for (int i = 0; i < x.length - 1; i++) {
            int diff = x[i + 1] - x[i];
            if (diff > maxDiff) maxDiff = diff;
        }
        return maxDiff;
    }
}
