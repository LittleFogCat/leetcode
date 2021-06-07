package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.structs.heap.MaxHeap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P1046_LastStoneWeight {
    public int lastStoneWeight3(int[] stones) {
        Arrays.sort(stones);
        int len = stones.length; // 还剩余前len个石头
        while (len > 1) {
            int max1 = stones[len - 1];
            int max2 = stones[len - 2];
            if (max1 == max2) {
                len -= 2; // 直接2个都舍弃
            } else {
                stones[len - 2] = max1 - max2; // 剩下的残片放在最后
                len--; // 舍弃一个
                Arrays.sort(stones, 0, len); // 保持有序
                // 这一句这样写可以把时间复杂度降低到O(n^2)：
                //
                // for (int toSort = len - 1; toSort > 0 && stones[toSort - 1] > stones[toSort]; toSort--) {
                //     int t = stones[toSort];
                //     stones[toSort] = stones[toSort - 1];
                //     stones[toSort - 1] = t;
                // }
                // 但是事实上在java中对于这种有序度很高的数组排序的时间复杂度近似于O(n)，
                // 所以实际上使用Arrays.sort()并不会将时间复杂度提高很多。
                // 但是数组元素的移动是不可避免的，所以单步时间复杂度仍是O(n)。
                // 虽然System.arrayCopy
            }
        }
        return len == 0 ? 0 : stones[0];
    }

    public int lastStoneWeight2(int[] stones) {
        MaxHeap heap = new MaxHeap(stones);
        while (heap.size() > 1) {
            int a = heap.poll();
            int b = heap.poll();
            if (a != b) heap.offer(Math.abs(a - b));
        }
        return heap.size() == 0 ? 0 : heap.poll();
    }

    public int lastStoneWeight1(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> -a));
        for (int stone : stones) {
            queue.offer(stone);
        }
        while (queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();
            if (a != b) queue.offer(Math.abs(a - b));
        }
        return queue.size() == 0 ? 0 : queue.poll();
    }

    int len;
    int[] heap;

    public int lastStoneWeight(int[] stones) {
        heap = new int[stones.length];
        for (int value : stones) offer(value);
        while (len > 1) {
            int a = poll();
            int b = poll();
            if (a != b) {
                offer(Math.abs(a - b));
            }
        }
        return len == 0 ? 0 : poll();
    }

    public void offer(int num) {
        heap[len] = num;
        int pos = len, parent;
        while (pos != 0 && heap[parent = (pos - 1) / 2] < heap[pos]) {
            swap(pos, pos = parent);
        }
        len++;
    }

    public int poll() {
        int top = heap[0];
        swap(0, --len);
        int pos = 0;
        while (true) {
            int leftSon = pos * 2 + 1;
            if (leftSon >= len) break; // 没有孩子
            int larger;
            if (leftSon == len - 1) { // 没有右孩子
                if (heap[pos] >= heap[leftSon]) break;
                else larger = leftSon;
            } else { // 有右孩子
                if (heap[pos] >= heap[leftSon] && heap[pos] >= heap[leftSon + 1]) break;
                else larger = heap[leftSon] > heap[leftSon + 1] ? leftSon : leftSon + 1;
            }
            swap(pos, pos = larger);
        }
        return top;
    }

    private void swap(int p, int q) {
        int t = heap[p];
        heap[p] = heap[q];
        heap[q] = t;
    }

}
