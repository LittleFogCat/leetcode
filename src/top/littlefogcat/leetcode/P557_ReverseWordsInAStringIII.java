package top.littlefogcat.leetcode;

public class P557_ReverseWordsInAStringIII {
    class Solution {
        public String reverseWords(String s) {
            int start = 0;
            int end = s.indexOf(' ');
            if (end == -1) return reverse(s);
            StringBuilder sb = new StringBuilder();
            completeString(sb, s, start, end);
            return sb.toString();
        }

        private void completeString(StringBuilder builder, String s, int start, int end) {
            builder.append(reverse(s.substring(start, end))).append(' ');
            if (end == s.lastIndexOf(' ')) {
                builder.append(reverse(s.substring(end + 1, s.length())));
            } else {
                completeString(builder, s, end + 1, s.indexOf(' ', end + 1));
            }
        }

        private String reverse(String s) {
            char[] chars = s.toCharArray();
            int len = chars.length - 1;
            int mid = chars.length / 2;
            for (int i = 0; i < mid; i++) {
                char tmp = chars[i];
                chars[i] = chars[len - i];
                chars[len - i] = tmp;
            }
            return String.valueOf(chars);
        }
    }
}
