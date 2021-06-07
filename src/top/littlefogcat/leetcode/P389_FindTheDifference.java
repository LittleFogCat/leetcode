package top.littlefogcat.leetcode;

public class P389_FindTheDifference {
    public char findTheDifference(String s, String t) {
        char c = 0;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (char ch : tc) {
            c ^= ch;
        }
        for (char ch : sc) {
            c ^= ch;
        }
        return c;
    }
}
