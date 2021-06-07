package top.littlefogcat.leetcode;

public class P55_JumpGame {
    public boolean canJump(int[] nums) {
        for (int i = 0, max = 0; i <= max; i++) {
            int curMax = i + nums[i];
            if (curMax > max) max = curMax;
            if (max >= nums.length - 1) return true;
        }
        return false;
    }

}
