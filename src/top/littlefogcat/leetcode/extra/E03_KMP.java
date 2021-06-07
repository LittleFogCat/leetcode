package top.littlefogcat.leetcode.extra;

import java.util.Arrays;

public class E03_KMP {

    int kmp(String s, String p) {
        int[] next = next(p.toCharArray());

        // s指针
        int i = 0 /* s指针 */, j = 0 /* p指针 */; // 双指针
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) { // p[0] 匹配不上，那么s指针右移一位
                i++;
            } else { // p 右移
                j = next[j];
            }
        }
        return j == p.length() ? i - j : -1;
    }

    int[] next(char[] p) {
        int[] next = pmt(p);
        System.out.println("p: " + Arrays.toString(p));
        System.out.println("pmt: " + Arrays.toString(next));
        if (next.length > 0) System.arraycopy(next, 0, next, 1, next.length - 1);
        next[0] = 0;
        return next;
    }

    // 双指针计算pmt数组
    int[] pmt(char[] p) {
        int[] pmt = new int[p.length];
        pmt[0] = 0;
        // 双指针，i在j右边
        for (int i = 1, j = 0; i < p.length; ) {
            if (j == 0 && p[i] != p[j]) {
                pmt[i] = 0;
                i++;
            } else if (p[i] == p[j]) {
                pmt[i] = j + 1;
                i++;
                j++;
            } else { // p[i]!= p[j] && j!= 0
                // 在i、j处失配，但是之前的都是匹配的
                j = pmt[j - 1];
            }
        }
        return pmt;
    }

    public static void main(String[] args) {
        E03_KMP e03 = new E03_KMP();
        String s = "aabaaabaaac";
        String p = "aabaaac";
        System.out.println(e03.kmp(s, p));
    }
}
