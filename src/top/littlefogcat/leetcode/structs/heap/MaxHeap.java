package top.littlefogcat.leetcode.structs.heap;

import java.util.Arrays;

/**
 * 大根堆
 */
public class MaxHeap {
    int[] heap;
    int len;

    public MaxHeap(int[] arr) {
        heap = arr;
        buildHeap();
    }

    private void buildHeap() {
        for (int item : heap) offer(item);
    }

    public int size() {
        return len;
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

    public void offer(int num) {
        if (len > heap.length + 1) { // 扩容
            heap = Arrays.copyOf(heap, (int) (heap.length * 1.5));
        }
        heap[len] = num;
        int pos = len, parent;
        while (pos != 0 && heap[parent = (pos - 1) / 2] < heap[pos]) {
            swap(pos, pos = parent);
        }
        len++;
    }

    private void swap(int p, int q) {
        int t = heap[p];
        heap[p] = heap[q];
        heap[q] = t;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[" + heap[0]);
        for (int i = 1; i < len; i++) {
            s.append(", ").append(heap[i]);
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(new int[]{2, 7, 4, 1, 8, 1});
        System.out.println(heap);
        System.out.println(heap.poll());
        System.out.println(heap);
        System.out.println(heap.poll());
        System.out.println(heap);
        System.out.println(heap.poll());
        System.out.println(heap);
        System.out.println(heap.poll());
        System.out.println(heap);
        System.out.println(heap.poll());
        System.out.println(heap);
    }
}
