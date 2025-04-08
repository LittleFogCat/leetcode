package top.littlefogcat.leetcode.structs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 并查集
 *
 * @param <T> 节点类型
 */
public class UnionFind<T> {
    protected int size;
    protected final Map<T, T> parent = new HashMap<>();

    public UnionFind() {
    }

    /**
     * @param orig 原始数据集
     */
    public UnionFind(T[] orig) {
        for (T t : orig) {
            parent.put(t, t); // 每个节点作为一个根节点
        }
        size = orig.length;
    }

    /**
     * 合并两个节点所在的树
     */
    public void union(T node1, T node2) {
//        System.out.println("union: " + node1 + ", " + node2);
        T root1 = find(node1);
        T root2 = find(node2);
        if (root1 != root2) {
            parent.put(root2, root1); // 使树2成为树1子树
            size--;
        }
    }

    /**
     * 查询节点node所在树的根节点
     */
    public T find(T node) {
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

    @Override
    public String toString() {
        Map<T, Set<T>> map = new HashMap<>(); // root - Set
        for (T item : parent.keySet()) {
            T root = find(item);
            Set<T> set = map.computeIfAbsent(root, T -> new HashSet<>());
            set.add(item);
        }
        return map.values().toString();
    }
}
