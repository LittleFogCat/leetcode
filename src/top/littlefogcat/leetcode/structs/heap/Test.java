package top.littlefogcat.leetcode.structs.heap;

public class Test {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        heap.buildHeap();
        System.out.println(heap);
    }
}
