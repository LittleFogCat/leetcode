package top.littlefogcat.leetcode.structs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 并查集
 */
public class UnionFind<T> {
    public int size;
    private final Map<T, T> parent = new HashMap<>();

    public UnionFind(T[] orig) {
        for (T t : orig) {
            parent.put(t, t); // 作为根节点
        }
        size = orig.length;
    }

    public void union(T t1, T t2) {
        System.out.println("union: " + t1 + ", " + t2);
        T root1 = findRoot(t1);
        T root2 = findRoot(t2);
        if (root1 != root2) {
            parent.put(root2, root1);
            size--;
        }
    }

    private T findRoot(T t) {
        if (!parent.containsKey(t)) return null;
        while (parent.get(t) != t) t = parent.get(t);
        return t;
    }

    @Override
    public String toString() {
        Map<T, Set<T>> map = new HashMap<>(); // root - Set
        for (T item : parent.keySet()) {
            T root = findRoot(item);
            Set<T> set = map.computeIfAbsent(root, T -> new HashSet<>());
            set.add(item);
        }
        return map.values().toString();
    }

    public static void main(String[] args) {
        UnionFind<Integer> uf = new UnionFind<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(uf);
        uf.union(1, 2);
        System.out.println(uf);
        uf.union(3, 4);
        System.out.println(uf);
        uf.union(4, 5);
        System.out.println(uf);
        uf.union(6, 8);
        System.out.println(uf);
        uf.union(6, 2);
        System.out.println(uf);
    }

    public static class UnionFind0 {
        public int size;
        private final Map<Integer, Integer> parent = new HashMap<>();

        public UnionFind0(int[] orig) {
            for (int t : orig) {
                parent.put(t, t); // 作为根节点
            }
            size = orig.length;
        }

        public void union(int i1, int i2) {
            int root1 = find(i1);
            int root2 = find(i2);
            if (root1 != root2) {
                parent.put(root2, root1);
                size--;
            }
        }

        private int find(int i) {
            while (parent.get(i) != i) i = parent.get(i);
            return i;
        }
    }
}
