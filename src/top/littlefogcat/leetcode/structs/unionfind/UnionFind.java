package top.littlefogcat.leetcode.structs.unionfind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 并查集
 */
public class UnionFind {
    protected int size;
    protected final Map<Integer, Integer> parent = new HashMap<>();

    public UnionFind(int[] orig) {
        for (int t : orig) {
            parent.put(t, t);
        }
        size = orig.length;
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parent.put(root2, root1);
            size--;
        }
    }

    public int find(int node) {
        if (!parent.containsKey(node)) {
            parent.put(node, node);
            size++;
            return node;
        }
        while (parent.get(node) != node) node = parent.get(node);
        return node;
    }

    public int size() {
        return size;
    }

    public String toString() {
        Map<Integer, Set<Integer>> map = new HashMap<>(); // root - Set
        for (Integer item : parent.keySet()) {
            Integer root = find(item);
            Set<Integer> set = map.computeIfAbsent(root, T -> new HashSet<>());
            set.add(item);
        }
        return map.values().toString();
    }
}
