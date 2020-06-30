package top.littlefogcat.leetcode;

public class P908_SmallestRangeI {
    class Solution {
        public int smallestRangeI(int[] A, int K) {
            int max, min;
            max = min = A[0];
            for (int a : A) {
                if (a > max) max = a;
                if (a < min) min = a;
            }
            return max - min > K * 2 ? max - min - K * 2 : 0;
        }
    }
}
