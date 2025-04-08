package top.littlefogcat.leetcode;

/**
 * 思路：使用栈。
 */
public class P150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1];
        int size = 0;
        for (String token : tokens) {
            if (token.length() > 1 || token.charAt(0) >= '0') {
                stack[size++] = Integer.parseInt(token);
            } else {
                int right = stack[--size], left = stack[--size];
                int ans = switch (token) {
                    case "+" -> left + right;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    case "/" -> left / right;
                    default -> 0;
                };
                stack[size++] = ans;
            }
        }
        return stack[0];
    }

    /**
     * 递归方式
     */
    public int evalRPN1(String[] tokens) {
        index = tokens.length - 1;
        return calculate(tokens);
    }

    int index = 0;

    private int calculate(String[] tokens) {
        String token = tokens[index--];
        if (token.length() > 1 || token.charAt(0) >= '0') {
            return Integer.parseInt(token);
        } else {
            int right = calculate(tokens);
            int left = calculate(tokens);
            return switch (token) {
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                case "/" -> left / right;
                default -> 0;
            };
        }
    }

}
