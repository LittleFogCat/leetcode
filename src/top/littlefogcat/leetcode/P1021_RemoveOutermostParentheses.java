package top.littlefogcat.leetcode;

public class P1021_RemoveOutermostParentheses {
    class Solution {
        public String removeOuterParentheses(String S) {
            StringBuilder r = new StringBuilder();
            int len = S.length();
            char[] cs = S.toCharArray();
            int flag = 0;
            int start = 0;
            for (int i = 0; i < len; i++) {
                char c = cs[i];
                if (c == '(') {
                    if (flag == 0) {
                        start = i;
                    }
                    flag++;
                } else if (c == ')') {
                    if (flag == 1) {
                        r.append(String.valueOf(cs, start + 1, i - start - 1));
                    }
                    flag--;
                }
            }

            return r.toString();
        }
    }

    class Solution1 {
        public String removeOuterParentheses(String S) {
            StringBuilder r = new StringBuilder();
            int len = S.length();
            int flag = 0;

            for (int i = 0; i < len; i++) {
                char c = S.charAt(i);
                if (c == '(') {
                    if (flag != 0) {
                        r.append(c);
                    }
                    flag++;
                } else if (c == ')') {
                    if (flag != 1) {
                        r.append(c);
                    }
                    flag--;
                }
            }

            return r.toString();
        }
    }
}
