package top.littlefogcat.leetcode;

public class P344_ReverseString {
    class Solution {
        public String reverseString(String s) {
            if (s == null) {
                return null;
            }
            if ("".equals(s)) {
                return "";
            }
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length / 2; i++) {
                int j = chars.length - 1 - i;
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
            }
            return String.valueOf(chars);
        }
    }
}
