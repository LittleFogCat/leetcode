package top.littlefogcat.leetcode;

public class P376_WiggleSubsequence {
    @SuppressWarnings("UseCompareMethod")
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int state = nums[1] > nums[0] ? 1 : nums[1] == nums[0] ? 0 : -1; // 上升1 下降-1 未知0
        int count = state == 0 ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] && state != 1) { // 转换状态到上升
                count++;
                state = 1;
            } else if (nums[i] < nums[i - 1] && state != -1) { // 转换状态到下降
                count++;
                state = -1;
            }
        }
        return count;
    }
}
