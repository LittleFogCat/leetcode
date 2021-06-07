package top.littlefogcat.leetcode;

public class P58_LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int length = 0;
        char[] chars = s.toCharArray();
        int i = chars.length - 1;
        for (; i >= 0; i--) {
            if (chars[i] != ' ') break;
        }
        for (; i >= 0; i--) {
            if (chars[i] == ' ') break;
            length++;
        }
        return length;
    }
}
