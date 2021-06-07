package top.littlefogcat.leetcode;

public class P80_RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int size = 1;
        for (int i = 1, count = 1; i < nums.length; ) {
            count = nums[i] == nums[i - 1] ? count + 1 : 1;
            if (count <= 2) nums[size++] = nums[i++];
            else { // 快进到下一个不相同的数
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return size;
    }
}
