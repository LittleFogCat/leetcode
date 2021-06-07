package top.littlefogcat.leetcode.structs.heap;

/**
 * 构建topk问题的最小堆
 */
public class TopKHeap extends MinHeap {
    public TopKHeap(int[] src, int heapSize) {
        super(src, heapSize);
    }

    public void update(int value) {
        // 如果小于堆中的最小值，那么就丢弃掉
        if (value < heap[0]) return;
        // 从顶部下沉
        heap[0] = value;
        int heapSize = heap.length;
        int pos = 0, left, right;
        while (true) {
            left = left(pos);
            right = left + 1;
            // ---------------------------------
            // 如果pos是三者中最小的，那么退出循环
            // 否则，与left、right之间较小的交换下沉
            // ---------------------------------
            if (left >= heapSize) break;
            if (right >= heapSize) { // 只比较left
                if (heap[pos] < heap[left]) break;
                swap(pos, left);
                pos = left;
            } else { // left、right都比较
                int min = heap[left] > heap[right] ? right : left;
                if (heap[pos] < heap[min]) break;
                swap(pos, min);
                pos = min;
            }
        }
    }
}
