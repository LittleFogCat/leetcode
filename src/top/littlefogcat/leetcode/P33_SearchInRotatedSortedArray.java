package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 找到两个上升序列的交界点（找最大值）
        int max = 0;
        while (left <= right) {
            if (nums[left] <= nums[right]) { // 只剩一个上升序列
                max = nums[right] > nums[max] ? right : max;
                break;
            }
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[max]) max = mid;
            if (nums[mid] < nums[right]) { // mid位于序列2中，应在左边找
                right = mid - 1;
            } else { // mid位于序列1中，往右边找
                left = mid + 1;
            }
        }
        System.out.println(max);
        int r = target >= nums[0] ? Arrays.binarySearch(nums, 0, max + 1, target)
                : Arrays.binarySearch(nums, max + 1, nums.length, target);
        return r < 0 ? -1 : r;
    }
}
