package top.littlefogcat.leetcode;

public class P34_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        // 是否存在
        int index = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (index == -1) return result;
        // 找左边
        left = 0;
        right = index;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target && (mid == 0 || nums[mid - 1] < target)) {
                result[0] = mid;
                break;
            } else if (nums[mid] == target) { // 往左边找
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 找右边
        left = index;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] > target)) {
                result[1] = mid;
                break;
            } else if (nums[mid] == target) { // 往右边找
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public int[] searchRange1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 二分查找
        int index = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (index == -1) return new int[]{-1, -1};
        left = right = index;
        // 扩散
        while (left != 0 && nums[left - 1] == nums[left]) left--;
        while (right != nums.length - 1 && nums[right + 1] == nums[right]) right++;
        return new int[]{left, right};
    }
}
