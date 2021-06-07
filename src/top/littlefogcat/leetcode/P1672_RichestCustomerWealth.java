package top.littlefogcat.leetcode;

public class P1672_RichestCustomerWealth {
    public int maximumWealth(int[][] accounts) {
        int max = -1;
        for (int[] account : accounts) {
            int wealth = 0;
            for (int w : account) {
                wealth += w;
            }
            if (wealth > max) max = wealth;
        }
        return max;
    }
}
