package top.littlefogcat.leetcode.extra;

import java.util.Arrays;

@SuppressWarnings("StatementWithEmptyBody")
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

    int[] next(char[] s) {
        int[] next = pmt(s);
        if (next.length - 1 >= 0) System.arraycopy(next, 0, next, 1, next.length - 1);
        next[0] = 0;
        return next;
    }

    /**
     * -     i
     * -     ↓
     * a a b c
     * a b c a
     */
    int[] pmt(char[] s) {
        int[] pmt = new int[s.length];
        pmt[0] = 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i] == s[pmt[i - 1]]) {
                pmt[i] = pmt[i - 1] + 1;
            }
        }
        return pmt;
    }

    public static void main(String[] args) {
        E03_KMP e03 = new E03_KMP();
        String s = "abababca";
        String p = "cb";
        System.out.println(e03.kmp(s, p));
    }
}
