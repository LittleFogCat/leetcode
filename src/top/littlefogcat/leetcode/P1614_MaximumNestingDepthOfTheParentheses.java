package top.littlefogcat.leetcode;

public class P1614_MaximumNestingDepthOfTheParentheses {
    public int maxDepth(String s) {
        int len = s.length();
        int depth = 0;
        int maxDepth = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') depth++;
            if (c == ')') depth--;
            if (depth > maxDepth) maxDepth = depth;
        }
        return maxDepth;
    }
}
