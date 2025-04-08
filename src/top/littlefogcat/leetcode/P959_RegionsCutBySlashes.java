package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.structs.unionfind.UnionFindArray;

public class P959_RegionsCutBySlashes {

    /**
     * 递归DFS
     *
     * 思路：每一个网格斜对角相连可以划分为4个小三角形。
     * 其中grid[i][j]上面为(2i,2j+1)，左面为(2i,2j)，下面为(2i+1,2j)，右面为(2i+1,2j+1)。
     * 标记每个小三角形的区域。
     */
    public int regionsBySlashes(String[] grid) {
        int areas = 0;
        boolean[][] checked = new boolean[grid.length * 2][grid.length * 2];
        char[][] chGrid = new char[grid.length][];
        for (int i = 0; i < chGrid.length; i++) {
            chGrid[i] = grid[i].toCharArray();
        }
        for (int i = 0; i < checked.length; i++) {
            for (int j = 0; j < checked.length; j++) {
                if (checked[i][j]) continue;
                System.out.println(i + ", " + j);
                check(checked, i, j, chGrid);
                areas++;
            }
        }
        return areas;
    }

    /**
     * 采用中心扩散法，将该区域所有三角空格标记。
     */
    private void check(boolean[][] checked, int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i >= checked.length || j >= checked[0].length || checked[i][j]) return;
        checked[i][j] = true;
        char ch = grid[i / 2][j / 2]; // 字符
        if (i % 2 == 0 && j % 2 == 0) { // 左
            check(checked, i + 1, j - 1, grid);
            if (ch != '/') check(checked, i + 1, j, grid);
            if (ch != '\\') check(checked, i, j + 1, grid);
        } else if (i % 2 == 0) { // 上
            check(checked, i - 1, j - 1, grid);
            if (ch != '/') check(checked, i + 1, j, grid);
            if (ch != '\\') check(checked, i, j - 1, grid);
        } else if (j % 2 == 0) { // 下
            check(checked, i + 1, j + 1, grid);
            if (ch != '/') check(checked, i - 1, j, grid);
            if (ch != '\\') check(checked, i, j + 1, grid);
        } else { // 右
            check(checked, i - 1, j + 1, grid);
            if (ch != '/') check(checked, i - 1, j, grid);
            if (ch != '\\') check(checked, i, j - 1, grid);
        }
    }

    /**
     * 并查集
     */
    public int regionsBySlashes1(String[] grid) {
        UnionFindArray uf = new UnionFindArray(4000);
        for (int i = 0, gridLength = grid.length; i < gridLength; i++) {
            String row = grid[i];
            for (int j = 0; j < row.length(); j++) {
                char ch = row.charAt(j);
                // 四个小三角形的id
                int left = 120 * i + 4 * j;
                int top = left + 1;
                int right = left + 2;
                int bottom = left + 3;
                // 内部合并
                if (ch == ' ') {
                    uf.union(left, top);
                    uf.union(top, right);
                    uf.union(right, bottom);
                } else if (ch == '/') {
                    uf.union(left, top);
                    uf.union(right, bottom);
                } else if (ch == '\\') {
                    uf.union(top, right);
                    uf.union(left, bottom);
                }
                // 外部合并
                if (i != 0) { // top 和 (i-1, j) 的 bottom 合并
                    int outsideId = 120 * (i - 1) + 4 * j + 3;
                    uf.union(top, outsideId);
                }
                if (j != 0) { // left 和 (i, j-1) 的 right 合并
                    int outsideId = 120 * i + 4 * (j - 1) + 2;
                    uf.union(left, outsideId);
                }
            }
        }
        return uf.size();
    }
}

