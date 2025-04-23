package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.*;

public class P3249_CounttheNumberofGoodNodes {
    List<Integer>[] neighborMap;
    int ans = 0;

    public int countGoodNodes(int[][] edges) {
        neighborMap = new List[edges.length + 1];
        for (int[] edge : edges) {
            addNeighbor(edge[0], edge[1]);
            addNeighbor(edge[1], edge[0]);
        }
        calculateSize(-1, 0);
        return ans;
    }

    private void addNeighbor(int i, int j) {
        List<Integer> list = neighborMap[i];
        if (list == null) list = new ArrayList<>();
        list.add(j);
        neighborMap[i] = list;
    }

    private int calculateSize(int parent, int node) {
        List<Integer> children = neighborMap[node];
        if (children.size() == 1 && children.get(0) == parent) {
            ans++;
            return 1;
        }
        int size = 1;
        int expectedSize = 0;
        for (int child : children) {
            if (child == parent) continue;
            int childSize = calculateSize(node, child);
            size += childSize;
            if (expectedSize == 0) expectedSize = childSize;
            else if (expectedSize != childSize) expectedSize = -1;
        }
        if (expectedSize > 0) {
            ans++;
        }
        return size;
    }

    public static void main(String[] args) {
        P3249_CounttheNumberofGoodNodes solution = new P3249_CounttheNumberofGoodNodes();
        int[][] arr = Util.convertTo2dIntArray("[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]");
        int count = solution.countGoodNodes(arr);
        System.out.println(count);
//        System.out.println(solution.countGoodNodes(Util.read2dIntArrayFromFile("TestCase_P3249")));
    }
}
