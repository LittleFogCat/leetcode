package top.littlefogcat.leetcode;

import java.util.*;

public class P22_GenerateParentheses {
    /**
     * 可以把问题看做是在现有的字符串中插入括号：
     * n = 1：{"()"}
     * n = 2：
     * 在字符串"()"中插入括号，分别可以插入到0、1、2位置：
     * "()()"
     * "(())"
     * "()()"
     * 其中，0、2重复，所以剩余两项
     * {"()()", "(())"}
     * <p>
     * n = 3:
     * 分别在字符串{"()()", "(())"}插入括号：
     * "()()()"
     * "(())()"
     * "()()()"
     * "()(())"
     * "()()()"
     * <p>
     * "()(())"
     * "(()())"
     * "((()))"
     * "(()())"
     * "(())()"
     * <p>
     * 去重之后剩下{"()()()", "(())()", "()(())", "(()())", "((()))"}五项。
     */
    public List<String> generateParenthesis(int n) {
        if (n == 0) return Collections.singletonList("");
        if (n == 1) return Collections.singletonList("()");

        Set<String> set = new HashSet<>();
        List<String> prev = generateParenthesis(n - 1);
        for (String s : prev) {
            for (int pos = 0; pos < s.length(); pos++) {
                String newString = s.substring(0, pos) + "()" + s.substring(pos);
                if (!set.contains(newString)) {
                    set.add(newString);
                }
            }
        }

        return new ArrayList<>(set);
    }

    public List<String> generateParenthesis1(int n) {
        if (n == 0) return Collections.singletonList("");
        if (n == 1) return Collections.singletonList("()");
        List<String> r = new ArrayList<>();
        helper(2 * n, 0, 0, "", r);
        return r;
    }

    /**
     * targetLength: 需要返回的字符串的长度
     * left: 字符串中已经存在的左括号的个数
     * right: 字符串中已经存在的右括号的个数
     */
    public void helper(int targetLength, int left, int right, String currentString, List<String> r) {
        if (currentString.length() == targetLength) {
            r.add(currentString);
            return;
        }
        if (left < targetLength / 2) {
            helper(targetLength, left + 1, right, currentString + "(", r);
        }
        if (left > right) {
            helper(targetLength, left, right + 1, currentString + ")", r);
        }
    }

    public static void main(String[] args) {
        P22_GenerateParentheses p = new P22_GenerateParentheses();
        System.out.println(p.generateParenthesis(3));
    }

}
