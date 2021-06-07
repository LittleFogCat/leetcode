package top.littlefogcat.leetcode;

public class P35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                right = mid - 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] > target) return mid + 1;
                left = mid + 1;
            }
        }
        return -1;
    }
}
