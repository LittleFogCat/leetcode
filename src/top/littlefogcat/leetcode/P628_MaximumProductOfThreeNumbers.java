package top.littlefogcat.leetcode;

public class P628_MaximumProductOfThreeNumbers {
    // 只用找出最大的3个数与最小的2个数即可。
    public int maximumProduct(int[] nums) {
        int[] heap = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE}; // 前三项为最大的三个数的堆，后两项分别为最小数、第二小数

        for (int num : nums) {
            if (num > heap[0]) {
                heap[0] = num;
                // adjust
                int minInd = heap[2] > heap[1] ? 1 : 2;
                if (heap[minInd] < heap[0]) { // swap
                    int t = heap[0];
                    heap[0] = heap[minInd];
                    heap[minInd] = t;
                }
            }

            if (num < heap[3]) {
                heap[4] = heap[3];
                heap[3] = num;
            } else if (num < heap[4]) {
                heap[4] = num;
            }
        }
        return Math.max(heap[0] * heap[1] * heap[2], heap[3] * heap[4] * Math.max(heap[1], heap[2]));
    }
}
