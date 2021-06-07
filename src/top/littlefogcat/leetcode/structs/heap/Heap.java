package top.littlefogcat.leetcode.structs.heap;

import java.util.Arrays;

public abstract class Heap {
    protected int[] heap;

    public Heap(int[] src, int heapSize) {
        heap = new int[heapSize];
        System.arraycopy(src, 0, heap, 0, heapSize);
    }

    public void buildHeap() {
        for (int i = 1; i < heap.length; i++) {
            int pos = i;
            int parent;
            while (pos != 0 && compare(parent = parent(pos), pos) > 0) {
                swap(pos, parent);
                pos = parent;
            }
        }
    }

    /**
     * 比较两个数，传入其序号，如果在堆中，前者应该排列在后者之前，返回负数；否则返回正数。
     *
     * @see Comparable
     */
    abstract int compare(int index0, int index1);

    int left(int parent) {
        return parent * 2 + 1;
    }

    int right(int parent) {
        return parent * 2 + 2;
    }

    int parent(int child) {
        return (child - 1) / 2;
    }

    void swap(int p, int q) {
        int t = heap[p];
        heap[p] = heap[q];
        heap[q] = t;
    }

    public int[] toArray(boolean copy) {
        if (copy) return Arrays.copyOf(heap, heap.length);
        return heap;
    }

    public int[] toArray() {
        return toArray(true);
    }

    @Override
    public String toString() {
        return "Heap{" +
                "heap=" + Arrays.toString(heap) +
                '}';
    }
}
