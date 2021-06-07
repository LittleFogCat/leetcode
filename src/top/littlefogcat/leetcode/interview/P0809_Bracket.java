package top.littlefogcat.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

public class P0809_Bracket {
    public List<String> generateParenthesis(int n) {
        List<String> r = new ArrayList<>();
        generate(r, n, n, new char[n * 2], 0, 0);
        return r;
    }

    private void generate(List<String> result, int left, int right, char[] cur, int curLeft, int pos) {
        if (left == 0 && right == 0) {
            result.add(new String(cur));
        } else {
            if (left > 0) {
                cur[pos] = '(';
                generate(result, left - 1, right, cur, curLeft + 1, pos + 1); // 下一个字符是左括号
            }
            if (curLeft > 0) {
                cur[pos] = ')'; // 回溯，当前是右括号的情况
                generate(result, left, right - 1, cur, curLeft - 1, pos + 1); // 下一个字符是右括号
            }
        }
    }

    public static void main(String[] args) {
        P0809_Bracket p = new P0809_Bracket();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + p.generateParenthesis(i).size() + ", " + Math.pow(2,  i));
        }
    }

}
