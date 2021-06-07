package top.littlefogcat.leetcode;

import java.util.*;

public class P1486_XOROperationInAnArray {
    public int xorOperation0(int n, int start) {
        int r = start;
        while (--n > 0) r ^= start += 2;
        return r;
    }

    public int xorOperation(int n, int start) {
        int last = start & n & 1;
        int t = start >> 1; // 右移一位
        int newT = (t & 1) == 1 ? t + 1 : t; // 如果t为奇数，从t+1开始计算
        int newN = (t & 1) == 1 ? n - 1 : n; // 如果t为奇数，从t+1开始计算
        int mod = newN & 3; // n除以4的余数
        /*
         * 若n = 4i+1，f(n) = t + n - 1
         * 若n = 4i+2，f(n) = 1
         * 若n = 4i+3，f(n) = t + n
         * 若n = 4i，f(n) = 0
         */
        int fn = switch (mod) {
            case 1 -> newT + newN - 1;
            case 2 -> 1;
            case 3 -> newT + newN;
            default -> 0;
        };
        if (t != newT) fn ^= t; // 如果t为奇数，最后还要与t异或
        return (fn << 1) | last; // 左移一位，并加上最后一位
    }

}
