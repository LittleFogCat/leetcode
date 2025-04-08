package top.littlefogcat.leetcode.structs.unionfind;

import java.util.HashMap;
import java.util.Map;

/**
 * 节点类型为int的并查集。
 * 使用条件：元素类型为整型，且构造方法需要传入数据的。
 */
public class UnionFindInt {
    protected int size;
    protected final Map<Integer, Integer> parent = new HashMap<>();

    public UnionFindInt() {
    }

    /**
     * @param orig 原始数据集
     */
    public UnionFindInt(int[] orig) {
        for (int t : orig) {
            parent.put(t, t); // 每个节点作为一个根节点
        }
        size = orig.length;
    }

    /**
     * 合并两个节点所在的树
     */
    public void union(int node1, int node2) {
//        System.out.println("union: " + node1 + ", " + node2);
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parent.put(root2, root1); // 使树2成为树1子树
            size--;
        }
    }

    /**
     * 查询节点node所在树的根节点
     */
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
}
