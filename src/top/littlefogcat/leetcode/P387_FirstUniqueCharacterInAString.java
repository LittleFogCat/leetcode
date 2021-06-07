package top.littlefogcat.leetcode;

public class P387_FirstUniqueCharacterInAString {
    public int firstUniqChar1(String s) {
        char[] counts = new char[128];
        char[] str = s.toCharArray();
        for (char c : str) {
            counts[c]++;
        }
        for (int i = 0; i < str.length; i++) {
            if (counts[str[i]] == 1) return i;
        }
        return -1;
    }

}
