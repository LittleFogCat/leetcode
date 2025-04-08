package top.littlefogcat.leetcode;

public class P945_MinimumIncrementtoMakeArrayUnique {
    class Solution {
        int[] counts = new int[1024];

        public int minIncrementForUnique(int[] nums) {
            for (int num : nums) {
                if (num >= counts.length) expand(num);
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

        private void expand(int num) {
            int len = counts.length;
            while (len <= num) {
                len = len << 1;
            }
            int[] newArray = new int[len];
            System.arraycopy(counts, 0, newArray, 0, counts.length);
            counts = newArray;
        }
    }

    class Solution0 {
        int[] counts = new int[1024];

        public int minIncrementForUnique(int[] nums) {
            // 统计数字出现次数
            for (int num : nums) {
                if (num >= counts.length) expand(num);
                counts[num]++;
            }

            int ans = 0;
            int m = 0; // 待移动的数字个数
            for (int x = 0; x < counts.length; x++) {
                int n = counts[x];
                // 1. 增加了 n - 1 个需要移动的数字
                m += n - 1;
                if (m < 0) m = 0; // 不能移动负数个
                if (x == counts.length - 1) {
                    // 最后一项，没法移动了，那么就直接计算
                    // 需要移动 m + (m-1) + (m-2) + .. + 1 次，按照等差数列求和
                    ans += m * (m + 1) / 2;
                } else {
                    // 将 m 个 x 移动到 x + 1
                    // 由于每次移动一步，共移动了 m 个数字，则答案计数增加 m；
                    ans += m;
                }
            }
            return ans;
        }

        private void expand(int num) {
            int len = counts.length;
            while (len <= num) {
                len = len << 1;
            }
            int[] newArray = new int[len];
            System.arraycopy(counts, 0, newArray, 0, counts.length);
            counts = newArray;
        }
    }
}
