package top.littlefogcat.leetcode.structs.heap;

public class MinHeap extends Heap {
    public MinHeap(int[] src, int heapSize) {
        super(src, heapSize);
    }

    @Override
    int compare(int index0, int index1) {
        return heap[index0] - heap[index1];
    }
}
