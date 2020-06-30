package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P885_SpiralMatrixIII {
    private static int[][] r;

    public static void main(String[] args) {
        Solution solution = new P885_SpiralMatrixIII().new Solution();
        long time = TimeCounter.countRepeat(() -> r = solution.spiralMatrixIII(5, 6, 1, 4), 10000);
        System.out.println(Arrays.deepToString(r) + "\ntime = " + time);
    }

    class Solution {
        public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
            int[][] result = new int[R * C][2];
            int[] lastPoint = new int[]{r0, c0};
            result[0] = Arrays.copyOf(lastPoint, 2);
            for (int i = 1; i < result.length; ) {
                int[] next = nextPoint(R, C, r0, c0, lastPoint[0], lastPoint[1]);
                if (next[0] >= 0 && next[0] < R && next[1] >= 0 && next[1] < C) {
                    result[i] = next;
                    i++;
                }
                lastPoint = next;
            }
            return result;
        }

        /**
         * return the next point [r, c]
         *
         * @param R     total rows
         * @param C     total columns
         * @param r0    first point row
         * @param c0    first point column
         * @param lastR the last point row
         * @param lastC the last point column
         * @return the next point
         */
        public int[] nextPoint(int R, int C, int r0, int c0, int lastR, int lastC) {
            if (lastR > r0 && lastR - r0 >= lastC - c0 && lastR - r0 > c0 - lastC) {
                // should move to west
                if (lastC <= 0) { // cannot move to west
                    return nextPoint(R, C, r0, c0, r0 - (lastR - r0), lastC - 1);
                }
                return new int[]{lastR, lastC - 1};
            } else if (lastR <= r0 && r0 - lastR >= c0 - lastC && r0 - lastR >= lastC - c0) {
                // should move to east
                if (lastC >= C - 1) { // cannot move to east
                    return nextPoint(R, C, r0, c0, r0 + (r0 - lastR) + 1, lastC + 1);
                }
                return new int[]{lastR, lastC + 1};
            } else if (lastC < c0 && c0 - lastC >= lastR - r0 && c0 - lastC > r0 - lastR) {
                // should move to north
                if (lastR <= 0) { // cannot move to north
                    return nextPoint(R, C, r0, c0, lastR - 1, c0 + (c0 - lastC) + 1);
                }
                return new int[]{lastR - 1, lastC};
            } else {
                // should move to south
                if (lastR >= R - 1) { // cannot move to south
                    return nextPoint(R, C, r0, c0, lastR + 1, c0 - (lastC - c0));
                }
                return new int[]{lastR + 1, lastC};
            }
        }
    }
}
