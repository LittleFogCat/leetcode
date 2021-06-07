package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P31_NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) return;
        int pos = -1; // 最右边的一个正序对
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            // pos与newPos交换
            int newPos = pos + 1;
            while (newPos < nums.length - 1 && nums[newPos + 1] > nums[pos]) newPos++;
            swap(nums, pos, newPos);
        }
        // pos+1 ~ nums.length 倒序
        reverse(nums, pos + 1, nums.length - 1);
    }

    public static void swap(int[] arr, int p, int q) {
        int t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }

    private static void reverse(int[] arr, int p, int q) {
        while (p < q) {
            swap(arr, p, q);
            p++;
            q--;
        }
    }
}
