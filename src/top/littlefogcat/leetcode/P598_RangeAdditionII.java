package top.littlefogcat.leetcode;

public class P598_RangeAdditionII {
    public int maxCount(int m, int n, int[][] ops) {
        int minM = m, minN = n;
        for (int[] op : ops) {
            if (op[0] < minM) minM = op[0];
            if (op[1] < minN) minN = op[1];
        }
        return minM * minN;
    }
}
