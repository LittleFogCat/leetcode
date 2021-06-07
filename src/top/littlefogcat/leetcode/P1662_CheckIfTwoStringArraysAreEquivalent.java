package top.littlefogcat.leetcode;

public class P1662_CheckIfTwoStringArraysAreEquivalent {
    public boolean arrayStringsAreEqual1(String[] word1, String[] word2) {
        int len1 = 0;
        int len2 = 0;
        for (String s : word1) {
            len1 += s.length();
        }
        for (String s : word2) {
            len2 += s.length();
        }
        if (len1 != len2) return false;
        for (int i1 = 0, j1 = 0, i2 = 0, j2 = 0, cnt = 0; cnt < len1; cnt++, j2++, j1++) {
            while (j1 >= word1[i1].length()) {
                i1++;
                j1 = 0;
            }
            while (j2 >= word2[i2].length()) {
                i2++;
                j2 = 0;
            }
            if (word1[i1].charAt(j1) != word2[i2].charAt(j2)) return false;
        }
        return true;
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        if (word1.length + word2.length < 100) {
            String s1 = "";
            String s2 = "";
            for (String s : word1) {
                s1 += s;
            }
            for (String s : word2) {
                s2 += s;
            }
            return s1.equals(s2);
        }
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (String s : word1) {
            s1.append(s);
        }
        for (String s : word2) {
            s2.append(s);
        }
        return s1.toString().equals(s2.toString());
    }
}
