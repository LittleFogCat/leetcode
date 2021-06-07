package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ConstantConditions")
public class P1631_PathWithMinimumEffort {
    // -------- dijkstra算法 -------------
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        Point[][] points = new Point[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                points[i][j] = new Point(i, j, heights[i][j]);
            }
        }
        Point start = points[0][0]; // 起始点
        start.type = 'P';
        start.dis = 0;
        next(points, 0, 0, rows, cols, new HashSet<>());
        return points[rows - 1][cols - 1].dis;
    }

    private void next(Point[][] points, int x, int y, int rows, int cols, Set<Point> neighbors) { // 寻找下一个P点
        if (x == rows - 1 && y == cols - 1) return;
        // 将上下左右添加到相邻点中
        Point cur = points[x][y], up = null, down = null, left = null, right = null;
        if (x > 0) up = addPointToT(neighbors, points[x - 1][y], cur); // 上
        if (x < rows - 1) down = addPointToT(neighbors, points[x + 1][y], cur); // 下
        if (y > 0) left = addPointToT(neighbors, points[x][y - 1], cur); // 左
        if (y < cols - 1) right = addPointToT(neighbors, points[x][y + 1], cur); // 右
        // 变2：这里下一个点不用取最小的，只要小于等于当前即可
        // 因为当前点已经是目前为止的最小路径了，所以只有走上下左右的结果都大于当前点，
        // 才从备选点之中找。
        Point next = null;
        if (up != null && up.dis <= cur.dis) next = up;
        else if (down != null && down.dis <= cur.dis) next = down;
        else if (left != null && left.dis <= cur.dis) next = left;
        else if (right != null && right.dis <= cur.dis) next = right;
        else for (Point t : neighbors) {
                if (t.dis <= cur.dis) {
                    next = t;
                    break;
                } else if (next == null || t.dis < next.dis) next = t;
            }
        next.type = 'P';
        neighbors.remove(next);
        next(points, next.x, next.y, rows, cols, neighbors);
    }

    private Point addPointToT(Set<Point> neighbors, Point target, Point cur) {
        if (target.type == 'P') return null;
        int delta = Math.abs(target.val - cur.val);
        // 变1：这里不是最小距离，而是最小高度差
        target.dis = Math.min(target.dis, Math.max(cur.dis, delta));
        neighbors.add(target);
        return target;
    }

    private static class Point {
        int x, y, val;
        char type = 'T'; // T / P
        int dis = Integer.MAX_VALUE; // 距离起点的距离

        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
