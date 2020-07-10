package top.littlefogcat.leetcode;

import java.util.LinkedList;

public class P20_ValidParentheses {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.size() == 0 || c == '{' || c == '(' || c == '[') {
                stack.addLast(c);
            } else {
                char last = stack.getLast();
                if (last == '(' && c == ')' || last == '[' && c == ']' || last == '{' && c == '}') {
                    stack.removeLast();
                } else {
                    stack.addLast(c);
                }
            }
        }
        return stack.size() == 0;
    }
}
