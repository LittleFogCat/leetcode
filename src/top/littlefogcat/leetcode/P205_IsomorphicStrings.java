package top.littlefogcat.leetcode;

public class P205_IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        char[] charMap = new char[256]; // 前128项表示s->t，后128项表示t->s
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        for (int i = 0; i < S.length; i++) {
            int sIndex = S[i];
            if (charMap[sIndex] == 0) charMap[sIndex] = T[i];
            else if (charMap[sIndex] != T[i]) return false;
            int tIndex = T[i] + 128;
            if (charMap[tIndex] == 0) charMap[tIndex] = S[i];
            else if (charMap[tIndex] != S[i]) return false;
        }
        return true;
    }
}
