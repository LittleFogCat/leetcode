package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.structs.unionfind.UnionFind;
import top.littlefogcat.leetcode.structs.unionfind.UnionFindArray;

import java.util.*;

public class P947_MostStonesRemovedWithSameRowOrColumn {
    // --------------- HashMap -------------------

    public int removeStones1(int[][] stones) {
        boolean[] removed = new boolean[stones.length]; // 标记点是否删除
        Map<Integer, List<Integer>> rows = new HashMap<>(); // 行 - 该行的点
        Map<Integer, List<Integer>> cols = new HashMap<>(); // 列 - 该行的列
        for (int i = 0; i < stones.length; i++) {
            int[] stone = stones[i];
            List<Integer> sameRow = rows.computeIfAbsent(stone[0], integer -> new ArrayList<>());
            List<Integer> sameCol = cols.computeIfAbsent(stone[1], integer -> new ArrayList<>());
            sameRow.add(i);
            sameCol.add(i);
        }
        // 点系定义：
        // 1. 如果两个点是同一行或同一列，那么称这两个点为同一[点系]的。
        // 2. 如果A与B是同点系，B与C是同点系，那么A与C为同点系的。
        // 即，每个点系剩余一个点，最终剩余点的个数为点系的个数。
        int r = 0; // 点系的个数
        for (int i = 0; i < stones.length; i++) {
            if (removed[i]) continue;
            removePointSet(i, stones, removed, rows, cols); // 将这个点系的所有点标记为删除
            r++;
        }
        return stones.length - r;
    }

    private void removePointSet(int i, int[][] stones, boolean[] removed, Map<Integer, List<Integer>> rows, Map<Integer, List<Integer>> cols) {
        if (removed[i]) return; // 这个点已经删除了
        removed[i] = true;
        // System.out.println("删除：" + Arrays.toString(stones[i]));
        // 把这个点同行、同列的全删了
        List<Integer> sameRow = rows.get(stones[i][0]); // 同行的
        for (Integer stone : sameRow) {
            removePointSet(stone, stones, removed, rows, cols);
        }
        List<Integer> sameCol = cols.get(stones[i][1]); // 同列的
        for (Integer stone : sameCol) {
            removePointSet(stone, stones, removed, rows, cols);
        }
    }

    // ---------------- 并查集 --------------------

    public int removeStones(int[][] stones) {
        UnionFindArray0 uf = new UnionFindArray0();
        for (int[] stone : stones) {
            uf.union(stone[0], stone[1]);
        }
        return stones.length - uf.size;
    }

    class UnionFindArray0 {
        int size = 0;
        // key - 一个点的行或列，val - 该行/列对应的集合
        // 当x = root.get(x)说明该点是根节点。
        // 同一个集合的点，通过递归调用root.get(x)都能到达相同的根节点。
        private final int[] root = new int[20001];

        {
            Arrays.fill(root, -1);
        }

        public void union(int x, int y) {
            y += 10000; // 因为取值范围是1~10000，则x在1~10000取key，y在10001~20000取key。
            boolean containX = root[x] != -1;
            boolean containY = root[y] != -1;
            if (!containX && !containY) { // 增加一个新集合，用x为value来作为这个集合的标记
                root[x] = x;
                root[y] = x;
                size++; // 集合数+1
            } else if (!containX) { // 添加x行到当前集合
                root[x] = findRoot(y);
            } else if (!containY) { // 添加y列到当前集合
                root[y] = findRoot(x);
            } else { // 合并两个不相交的集合
                int set1 = findRoot(x);
                int set2 = findRoot(y);
                if (set1 != set2) { // 如果是两个不同的集合，将二者合并
                    root[set2] = set1; // 俯首称臣
                    size--; // 集合数-1
                }
            }
        }

        private int findRoot(int a) {
            while (a != root[a]) a = root[a];
            return a;
        }
    }

    // ---------------- 并查集OOP --------------------

    public int removeStones2(int[][] stones) {
        UnionFind uf = new UnionFind(new int[0]);
        for (int[] stone : stones) {
            uf.union(stone[0], stone[1] + 10000);
        }
        return stones.length - uf.size();
    }

    // ---------------- 并查集OOP Array --------------------

    public int removeStones3(int[][] stones) {
        UnionFindArray uf = new UnionFindArray(20001);
        for (int[] stone : stones) {
            uf.union(stone[0], stone[1] + 10000);
        }
        return stones.length - uf.size();
    }

}
