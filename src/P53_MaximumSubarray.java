public class P53_MaximumSubarray {
    class Solution {
        public int maxSubArray(int[] nums) {
            int max = nums[nums.length - 1];
            int lastMaxSum = nums[nums.length - 1];

            for (int i = nums.length - 2; i >= 0; i--) {
                int ai = nums[i];
                lastMaxSum = lastMaxSum > 0 ? ai + lastMaxSum : ai;
                max = Math.max(max, lastMaxSum);
            }
            return max;
        }
    }
}
