package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.structs.heap.TopKHeap;

import java.util.Arrays;
import java.util.Random;

import static top.littlefogcat.leetcode.Util.swap;

public class P215_KthLargestElementInAnArray {
    public int findKthLargest0(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest1(int[] nums, int k) {
        TopKHeap heap = new TopKHeap(nums, k);
        heap.buildHeap();
        for (int i = k; i < nums.length; i++) {
            heap.update(nums[i]);
        }
        return heap.toArray()[0];
    }

    /**
     * 使用从大到小的快排分区之后，对于nums[i]来讲，
     * 其左侧所有数大于它，而右侧所有数小于它。
     * 那么，如果i=k，那么它正好是第k大的；
     * 如果i<k，那么在右侧寻找第k-i大的数；
     * 如果i>k，那么在左侧寻找第k大的数。
     */
    public int findKthLargest(int[] nums, int k) {
        k = k - 1; // 将第k大转换为排序
        int p = 0, q = nums.length - 1;
        while (true) {
            int i = partition(nums, p, q);
            if (i - p == k) {
                return nums[i];
            } else if (i - p > k) {
                // find left
                q = i - 1;
            } else {
                // find right
                k = k + p - i - 1;
                p = i + 1;
            }
        }
    }

    private int partition(int[] nums, int p, int q) {
        randomSwap(nums, p, q);
        int i, j;
        for (i = p, j = p + 1; i <= q && j <= q; j++) {
            if (nums[i] < nums[j]) {
                swap(nums, i + 1, j);
                swap(nums, i, i + 1);
                i++;
            }
        }
        return i;
    }

    private Random random = new Random();

    private void randomSwap(int[] nums, int p, int q) {
        int offset = random.nextInt(q - p + 1);
        swap(nums, p, p + offset);
    }

    public static void main(String[] args) {
        P215_KthLargestElementInAnArray p = new P215_KthLargestElementInAnArray();
        p.findKthLargest(new int[]{7, 6, 5, 4, 3, 2, 1}, 5);
    }
}
