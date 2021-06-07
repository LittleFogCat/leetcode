package top.littlefogcat.leetcode;

import java.util.Arrays;

// 44
public class P7_ReverseInteger {
    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x /= 10;
        }
        return n != (int) n ? 0 : (int) n;
    }

    public static void main(String[] args) {
        P7_ReverseInteger p7 = new P7_ReverseInteger();
        System.out.println(p7.reverse(-123));
    }
}
