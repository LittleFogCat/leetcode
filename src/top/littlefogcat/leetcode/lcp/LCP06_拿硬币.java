package top.littlefogcat.leetcode.lcp;

public class LCP06_拿硬币 {
    public int minCount(int[] coins) {
        int c = 0;
        for (int coin : coins) c += (coin + 1) / 2;
        return c;
    }
}
