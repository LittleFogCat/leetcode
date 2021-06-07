package top.littlefogcat.leetcode;

// todo 不会
public class P803_BricksFallingWhenHit {
    int left = 1 << 3, top = 1 << 2, right = 1 << 1, bottom = 1, none = 0;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[][] depend = new int[grid.length][grid[0].length]; // 依赖矩阵
        // 建立依赖矩阵
        for (int i = 0; i < depend.length; i++) {
            for (int j = 0; j < depend[0].length; j++) {
                if (grid[i][j] == 0) continue;
                if (i == 0 || grid[i - 1][j] == 1) depend[i][j] |= top;
                if (j != 0 && grid[i][j - 1] == 1) depend[i][j] |= left;
                if (i != depend.length - 1 && grid[i + 1][j] == 1) depend[i][j] |= bottom;
                if (j != depend[0].length - 1 && grid[i][j + 1] == 1) depend[i][j] |= right;
            }
        }
        int[] removed = new int[hits.length];
        for (int i = 0; i < hits.length; i++) {
            int[] hit = hits[i];
            depend[hit[0]][hit[1]] = 0;
            removed[i] = remove(hit[0], hit[1], depend, none);
        }
        return removed;
    }

    // 删除一个点[direction]方向的依赖
    public int remove(int x, int y, int[][] depend, int direction) {
        System.out.println("将删除 (" + x + "," + y + ") 方向 " + direction);
        if (direction != none) {
            if (depend[x][y] == 0) return 0; // 本来就已经没有依赖了
            depend[x][y] &= ~direction; // 删除这个点对[direction]方向的依赖
        }
        int removed = 0;
        if (depend[x][y] == 0) { // 没有依赖的点了，将删除这个点
            System.out.println("删除 (" + x + "," + y + ")");
            removed = direction == none ? 0 : 1;
            removed += remove(x, y, depend, left); // 消除右边对该点的依赖
            removed += remove(x, y, depend, top); // 消除下边对该点的依赖
            removed += remove(x, y, depend, right); // 消除左边对该点的依赖
            removed += remove(x, y, depend, bottom); // 消除上边对该点的依赖
        }
        return removed;
    }
}
