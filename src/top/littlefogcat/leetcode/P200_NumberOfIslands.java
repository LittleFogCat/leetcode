package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.structs.UnionFind;
import top.littlefogcat.leetcode.structs.unionfind.UnionFindInt;

public class P200_NumberOfIslands {

    /**
     * 思路：并查集
     */
    public int numIslands(char[][] grid) {
        UnionFind<Integer> uf = new UnionFind<Integer>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') continue;
                int id = i * 300 + j; // 行/列最大300
                uf.union(id, id); // 添加该点
                if (i != 0 && j != 0) {
                    if ('1' == grid[i - 1][j]) uf.union(id, (i - 1) * 300 + j);
                    if ('1' == grid[i][j - 1]) uf.union(id, i * 300 + j - 1);
                } else if (i != 0) {
                    if ('1' == grid[i - 1][j]) uf.union(id, (i - 1) * 300);
                } else if (j != 0) {
                    if ('1' == grid[i][j - 1]) uf.union(id, j - 1);
                }
            }
        }
        return uf.size();
    }

    /**
     * 递归DFS
     */
    public int numIslands1(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') continue;
                islands++;
                check(grid, i, j);
            }
        }
        return islands;
    }

    private void check(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        check(grid, i - 1, j);
        check(grid, i, j - 1);
        check(grid, i + 1, j);
        check(grid, i, j + 1);
    }

}
