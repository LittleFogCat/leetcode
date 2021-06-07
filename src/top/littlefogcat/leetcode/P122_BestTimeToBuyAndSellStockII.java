package top.littlefogcat.leetcode;

public class P122_BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int benefit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) benefit += prices[i] - prices[i - 1];
        }
        return benefit;
    }
}
