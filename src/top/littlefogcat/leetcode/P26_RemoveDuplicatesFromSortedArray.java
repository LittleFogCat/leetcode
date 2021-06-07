package top.littlefogcat.leetcode;

public class P26_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int left, right;
        for (left = 0, right = 0; right < nums.length; right++) {
            if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                continue;
            }
            nums[left] = nums[right];
            left++;
        }
        return left;
    }
}
