package top.littlefogcat.leetcode;

public class P945_MinimumIncrementtoMakeArrayUnique {
    class Solution {
        int[] counts = new int[40001];

        public int minIncrementForUnique(int[] nums) {
            for (int num : nums) {
                counts[num]++;
            }

            int ans = 0;
            int toBeAdded = 0;
            for (int i = 0; i < counts.length; i++) {
                int ci = counts[i];
                ans += toBeAdded;
                toBeAdded += ci - 1;
                if (toBeAdded < 0) toBeAdded = 0;
            }
            // 还有?
            ans += toBeAdded * (toBeAdded + 1) / 2;
            return ans;
        }
    }
}
