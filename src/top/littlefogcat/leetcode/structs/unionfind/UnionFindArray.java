package top.littlefogcat.leetcode.structs.unionfind;

import java.util.*;

/**
 * 并查集
 */
public class UnionFindArray {
    protected int size;
    protected final int[] parent;

    public UnionFindArray(int arrLen) {
        parent = new int[arrLen];
        Arrays.fill(parent, -1);
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parent[root2] = root1;
            size--;
        }
    }

    public int find(int node) {
        if (parent[node] == -1) {
            parent[node] = node;
            size++;
            return node;
        }
        while (parent[node] != node) node = parent[node];
        return node;
    }

    public int size() {
        return size;
    }
}
