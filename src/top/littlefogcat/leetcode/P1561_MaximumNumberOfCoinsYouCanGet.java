package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P1561_MaximumNumberOfCoinsYouCanGet {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int count = piles.length / 3;
        int sum = 0;
        for (int i = piles.length - 2; count > 0; i -= 2, count--) {
            sum += piles[i];
        }
        return sum;
    }
}
