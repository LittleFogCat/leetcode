package top.littlefogcat.leetcode;

public class P1704_DetermineIfStringHalvesAreAlike {
    public boolean halvesAreAlike1(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length / 2; i++) {
            char c = cs[i];
            if (isYuan(c)) count++;
        }
        for (int i = cs.length / 2; i < cs.length; i++) {
            char c = cs[i];
            if (isYuan(c)) count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    private boolean isYuan(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public boolean halvesAreAlike(String s) {
        char[] cs = s.toCharArray();
        int[] c = new int[128];
        c['A'] = c['E'] = c['I'] = c['O'] = c['U'] = c['a'] = c['e'] = c['i'] = c['o'] = c['u'] = 1;
        int count = 0;
        int mid = cs.length / 2;
        for (int i = 0; i < mid; i++) {
            count += c[cs[i]] - c[cs[mid + i]];
        }
        return count == 0;
    }
}
