package top.littlefogcat.leetcode;

public class P1701_AverageWaitingTime {
    public double averageWaitingTime(int[][] customers) {
        int time = 0;
        long wait = 0;
        for (int[] c : customers) {
            if (c[0] >= time) {
                wait += c[1];
                time = c[0] + c[1];
            } else {
                wait += time - c[0] + c[1];
                time += c[1];
            }
        }
        return (0.0 + wait) / customers.length;
    }
}
