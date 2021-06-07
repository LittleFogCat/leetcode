package top.littlefogcat.leetcode;

public class P1266_MinimumTimeVisitingAllPoints {
    public int minTimeToVisitAllPoints(int[][] points) {
        int distance = 0;
        for (int i = 1; i < points.length; i++) {
            distance += distance(points[i], points[i - 1]);
        }
        return distance;
    }

    private int distance(int[] p1, int[] p2) {
        return Math.max(Math.abs(p1[0] - p2[0]), Math.abs(p1[1] - p2[1]));
    }

    public int minTimeToVisitAllPoints1(int[][] points) {
        for (int i = 1, distance = 0; ;
             distance += Math.max(
                     Math.abs(points[i][0] - points[i - 1][0]),
                     Math.abs(points[i][1] - points[i++ - 1][1])))
            if (i == points.length) return distance;
    }
}
