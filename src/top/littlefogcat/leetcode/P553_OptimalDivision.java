package top.littlefogcat.leetcode;

public class P553_OptimalDivision {
    class Solution {
        private final StringBuilder sb = new StringBuilder();

        public String optimalDivision(int[] nums) {
            int len = nums.length;
            if (len > 2) {
                sb.append(nums[0]).append("/(");
                for (int i = 1; i < len - 1; i++) {
                    sb.append(nums[i]).append('/');
                }
                sb.append(nums[len - 1]).append(')');
            } else if (len == 1) {
                return String.valueOf(nums[0]);
            } else if (len == 2) {
                return nums[0] + "/" + nums[1];
            } else {
                return "";
            }
            return sb.toString();
        }
    }
}
