package top.littlefogcat.leetcode;

public class P338_CountingBits {

    /**
     * O(n)解法
     * 偶数的比特和与其右移一位相同；
     * 奇数的比特和等于其右移一位的比特和加一；
     */
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i < bits.length; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    class Solution {
        public int[] countBits(int num) {
            int[] res = new int[num + 1];

            for (int i = 0; i < res.length; i++) {
                res[i] = countOne(i);
            }
            return res;
        }
    }

    private static int countOne(int i) {
        int count = 0;
        while (i != 0) {
            if ((i & 1) == 1) {
                count++;
            }
            i >>= 1;
        }
        return count;
    }
}
