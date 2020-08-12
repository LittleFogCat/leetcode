package top.littlefogcat.leetcode;

public class P27_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int left, right;
        for (left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] == val) continue;
            nums[left++] = nums[right];
        }
        return left;
    }
}
