package top.littlefogcat.leetcode;

public class P392_IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        for (int i = 0, j = 0; ; j++) {
            if (i == cs.length) return true;
            if (j == ct.length) return false;
            if (cs[i] == ct[j]) i++;
        }
    }
}
