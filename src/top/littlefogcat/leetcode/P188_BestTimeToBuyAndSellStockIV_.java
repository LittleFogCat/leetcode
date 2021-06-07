package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P188_BestTimeToBuyAndSellStockIV_ {
    //  状态转移方程式：
    //  dp[k][day][buy+1] = (buy == -1) ? max(dp[k][day+1][price[day]+1], dp[k][day+1][0]) :
    //      max((prices[day] - buy) + dp[k-1][day+1][0], dp[k][day+1][buy+1])
    //
    //  可以发现，第 day天的状态只与第day+1天的状态相关，那么：
    //  将 day+1 天的状态记作 dp、day 天的状态记作 dp'、当天价格为p、b=buy+1，则可以省略第二个day参数，变为递推式：
    //  dp'[k][b] = (b == 0) ? max(dp[k][p+1], dp[k][0]) :
    //      max(p-b+1+dp[k-1][0], dp[k][b])
    //
    //  以上。
    public int maxProfit1(int k, int[] prices) {
        if (k == 0 || prices.length < 2) return 0;
        int maxPrice = prices[0];
        for (int p : prices) if (p > maxPrice) maxPrice = p;
        int[][] dp = new int[prices.length][maxPrice + 2]; // dp[k][buy+1]
        int[][] dp1 = new int[prices.length][maxPrice + 2]; // 保存 day+1 天的状态
        // 初始化dp1，当day为最后一天：
        int lastPrice = prices[prices.length - 1];
        for (int kk = 0; kk < dp.length; kk++) {
            for (int bb = 1; bb < dp[0].length; bb++) { // bb = 0, buy = -1，说明没有买
                // 实际购买的价格 = bb-1
                if (kk > 0 && bb - 1 < lastPrice) { // 买入价小于当前价，卖出
                    dp1[kk][bb] = lastPrice - bb + 1;
                }  // 没有持有股票，或者买入价大于当前价，则没有利润
            }
        }
        System.out.println(Arrays.deepToString(dp1));
        for (int day = prices.length - 2; day >= 0; day--) {
            // dp'[k][b] = (b == 0) ? max(dp[k][p+1], dp[k][0]) :
            //                        max(p-b+1+dp[k-1][0], dp[k][b])
            int p = prices[day];
            for (int kk = 1; kk < dp.length; kk++) {
                for (int bb = 0; bb < dp[0].length; bb++) {
                    // buy+1 == bb
                    dp[kk][bb] = bb == 0 ? Math.max(dp1[kk][p + 1], dp1[kk][0]) :
                            Math.max(p - bb + 1 + dp1[kk - 1][0], dp1[kk][bb]);
                }
            }
            // 将dp赋值给dp1；因为dp无用了，所以交换即可
            int[][] tmp = dp1;
            dp1 = dp;
            dp = tmp;
        }
        return dp1[k][0];
    }

    private int dp(int[] prices, int k, int day, int buy, int[][] dp) {
        if (k == 0 || day == prices.length) return 0;
        if (dp[k][buy + 1] == 0) {
            // 三种情况：买、卖、无
            if (buy == -1) { // 买或无
                dp[k][buy + 1] = Math.max(
                        dp(prices, k, day + 1, prices[day], dp), // 买
                        dp(prices, k, day + 1, -1, dp) // 无
                );
            } else { // 卖或无
                dp[k][buy + 1] = Math.max(
                        (prices[day] - buy) + dp(prices, k - 1, day + 1, -1, dp), // 卖
                        dp(prices, k, day + 1, buy, dp) // 无
                );
            }
        }
        return dp[k][buy + 1];
    }

    private boolean isTrough(int[] prices, int day) { // 波谷
        if (day == 0) return prices[1] > prices[0];
        if (day == prices.length - 1) return prices[day] < prices[day - 1];
        return prices[day] < prices[day + 1] && prices[day] <= prices[day - 1];
    }

    // ---------------------------------------------

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length < 2) return 0;
        List<Integer> buys = new ArrayList<>(); // 底点
        List<Integer> sells = new ArrayList<>(); // 顶点
        boolean up = false; // 标记当前趋势是上涨还是下跌
        for (int i = 0; i < prices.length - 1; i++) {
            if (!up && prices[i + 1] > prices[i]) {
                buys.add(prices[i]);
                up = true;
            } else if (up && prices[i + 1] < prices[i]) { // 涨跌转换
                sells.add(prices[i]);
                up = false;
            }
        }
        if (up) sells.add(prices[prices.length - 1]);
        int len = buys.size();
        int[] min = new int[len]; // 目前为止股价的最小值
        int[] max = new int[len]; // 目前为止股价的最大值
        min[0] = buys.get(0);
        max[0] = sells.get(0);
        for (int i = 1; i < len; i++) {
            min[i] = Math.min(min[i - 1], buys.get(i));
            max[i] = Math.max(max[i - 1], sells.get(i));
        }

        int[] dp = new int[buys.size()];
        // TODO: 2020/12/29 dp[0]
        for (int kk = 0; kk < k; kk++) {
            for (int i = dp.length - 1; i >= 1; i--) {
                dp[i] = dp[i] + max[i - 1] - min[i - 1];
            }
        }
        return dp[0];
    }

    /**
     * dp[s][k] = max(bns[sell] - bns[buy] + dp[sell+1][k-1])
     * 其中buy >= s, sell > buy
     * <p>
     * ==>
     * <p>
     * dp[s] = max(bns[sell] - bns[buy] + dp'[sell+1])
     * 其中 s <= buy < sell
     */
    private int dp(int[] bns, int start, int k, int[][] dp) {
        if (start >= bns.length || k == 0) return 0;
        if (dp[start][k] != -1) return dp[start][k];
        int max = 0;
        for (int buy = start; buy < bns.length; buy += 2) {
            for (int sell = buy + 1; sell < bns.length; sell += 2) {
                if (bns[buy] < bns[sell]) {
                    int benefit = bns[sell] - bns[buy] + dp(bns, sell + 1, k - 1, dp);
                    if (benefit > max) max = benefit;
                }
            }
        }
        dp[start][k] = max;
        return max;
    }
}
