package top.littlefogcat.leetcode;

public class P1638_CountSubstringsThatDifferByOneCharacter {
    public int countSubstrings1(String s, String t) {
        if (s.length() > t.length()) {
            String tmp = s;
            s = t;
            t = tmp;
        }
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int count = 0;
        for (int len = 1; len <= cs.length; len++) {
            for (int i = 0; i < cs.length - len + 1; i++) {
                for (int j = 0; j < ct.length - len + 1; j++) {
                    int diff = 0;
                    for (int k = 0; k < len; k++) {
                        if (cs[i + k] != ct[j + k]) diff++;
                        if (diff > 1) break;
                    }
                    if (diff == 1) count++;
                }
            }
        }
        return count;
    }

    public int countSubstrings2(String s, String t) {
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int count = 0;
        // start[i][j] 表示 以s[i]与t[j]开头，相同的字符串长度
        int[][] start = new int[cs.length][ct.length];
        for (int i = cs.length - 1; i >= 0; i--) {
            for (int j = ct.length - 1; j >= 0; j--) {
                if (cs[i] != ct[j]) continue;
                start[i][j] = i == cs.length - 1 || j == ct.length - 1 ? 1 : start[i + 1][j + 1] + 1;
            }
        }
        // end[i][j] 表示 以s[i]与t[j]结尾，相同的字符串长度
        int[][] end = new int[cs.length][ct.length];
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < ct.length; j++) {
                if (cs[i] != ct[j]) continue;
                end[i][j] = i == 0 || j == 0 ? 1 : end[i - 1][j - 1] + 1;
            }
        }
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < ct.length; j++) {
                if (cs[i] == ct[j]) continue;
                int left = i > 0 && j > 0 ? end[i - 1][j - 1] : 0;
                int right = i < cs.length - 1 && j < ct.length - 1 ? start[i + 1][j + 1] : 0;
                count += (left + 1) * (right + 1);
            }
        }
        return count;
    }

}
