package top.littlefogcat.leetcode;

public class P1689_PartitioningIntoMinimumNumberOfDeciBinaryNumbers {
    public int minPartitions(String n) {
        char[] s = n.toCharArray();
        int max = '0';
        for (char c : s) {
            if (c == '9') {
                return 9;
            } else if (c > max) max = c;
        }
        return max - '0';
    }
}
