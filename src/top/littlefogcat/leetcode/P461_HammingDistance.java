package top.littlefogcat.leetcode;

public class P461_HammingDistance {
    public class Solution {
        public int hammingDistance(int x, int y) {
            int d = 0;
            int r = x ^ y;
            while (r != 0) {
                d += r & 1;
                r >>= 1;
            }
            return d;
        }
    }
}
