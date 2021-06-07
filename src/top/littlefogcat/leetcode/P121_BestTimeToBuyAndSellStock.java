package top.littlefogcat.leetcode;

public class P121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int minPrice = prices[0]; // 历史最低价
        int maxBenefit = 0; // 最大利润
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price < minPrice) minPrice = price;
            else if (price - minPrice > maxBenefit) maxBenefit = price - minPrice;
        }
        return maxBenefit;
    }

}
