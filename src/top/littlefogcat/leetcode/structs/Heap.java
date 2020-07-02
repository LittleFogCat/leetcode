package top.littlefogcat.leetcode.structs;

public abstract class Heap {
    private int[] heap;
    private int size;

    public Heap(int size) {
        heap = new int[size];
    }

    public Heap(int[] arr) {
        this(arr, false);
    }

    public Heap(int[] arr, boolean copy) {
        if (copy) {
            heap = new int[arr.length];
            System.arraycopy(arr, 0, heap, 0, arr.length);
        } else {
            heap = arr;
        }
    }

    abstract void buildHeap();

    abstract void adjustHeap();

    abstract void sort();

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
}
