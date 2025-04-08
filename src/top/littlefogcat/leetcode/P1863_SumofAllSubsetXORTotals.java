package top.littlefogcat.leetcode;

public class P1863_SumofAllSubsetXORTotals {

    /**
     * A[n] = a[n] + (a[n] xor A[n-1]) + A[n-1]
     */
    static class Solution {
        int ans = 0;

        public int subsetXORSum(int[] nums) {
            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];

            xor(nums, 0, 0);
            return ans;
        }

        public void xor(int[] nums, int start, int current) {
            if (start == nums.length) return;
            ans += current ^ nums[start];
            xor(nums, start + 1, current ^ nums[start]);
            xor(nums, start + 1, current);
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int r1 = solution.subsetXORSum(new int[]{1});
        int r2 = solution.subsetXORSum(new int[]{1, 3});
        int r3 = solution.subsetXORSum(new int[]{1, 5});
        int r4 = solution.subsetXORSum(new int[]{1, 5, 6});
        int r5 = solution.subsetXORSum(new int[]{3, 4, 5, 6, 7, 8});
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
        System.out.println(r5);
    }
}
