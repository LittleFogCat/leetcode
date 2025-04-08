package top.littlefogcat.leetcode;

public class P42_TrappingRainWater {
    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        for (int i = 0; i < height.length - 1; i++) {
            leftMax[i + 1] = Math.max(leftMax[i], height[i]);
        }
        for (int i = height.length - 1; i > 0; i--) {
            rightMax[i - 1] = Math.max(rightMax[i], height[i]);
        }
        int water = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (leftMax[i] <= height[i] || rightMax[i] <= height[i])
                continue;
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }
}
