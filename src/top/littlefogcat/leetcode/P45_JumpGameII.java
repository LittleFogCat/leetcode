package top.littlefogcat.leetcode;

public class P45_JumpGameII {
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0; // 长度1返回0
        int cur = 0;
        int step = 0;
        while (true) {
            int maxStep = nums[cur]; // 当前最大步数
            if (cur + maxStep >= nums.length - 1) return step + 1; // 能直接到终点
            int next = cur;
            int max = next;
            for (int i = 1; i <= maxStep; i++) {
                // 往前走i步，到达cur+i位置
                if (cur + i + nums[cur + i] > max) {
                    max = cur + i + nums[cur + i];
                    next = cur + i;
                }
            }
            cur = next;
            step++;
        }
    }

}
