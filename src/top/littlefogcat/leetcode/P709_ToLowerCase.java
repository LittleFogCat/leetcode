package top.littlefogcat.leetcode;

public class P709_ToLowerCase {

    class Solution {
        public String toLowerCase(String str) {
            char[] chars = str.toCharArray();
            int diff = 'A' - 'a';

            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c >= 'A' && c <= 'Z') {
                    chars[i] = (char) (c - diff);
                }
            }

            return new String(chars);
        }
    }
}
