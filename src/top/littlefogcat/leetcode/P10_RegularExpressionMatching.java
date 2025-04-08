package top.littlefogcat.leetcode;

// 40
public class P10_RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        // 用来保存函数运行的结果，1代表true，-1代表false，0代表未执行过
        int[][] arr = new int[s.length()][p.length()];
        return isMatch(s, 0, p, 0, arr);
    }

    private boolean isMatch(String s, int si, String p, int pi, int[][] arr) {
        boolean r;
        if (si == s.length()) {
            if (pi == p.length()) r = true;
            else if (pi == p.length() - 1) r = false;
            else if (p.charAt(pi + 1) == '*') r = isMatch(s, si, p, pi + 2, arr);
            else r = false;
        } else if (pi == p.length()) {
            r = false;
        } else {
            int result = arr[si][pi];
            if (result == 1) return true;
            if (result == -1) return false;

            char cs = s.charAt(si);
            char cp = p.charAt(pi);

            // 这是一个通配符
            if (pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                // 可以匹配，是否配？
                if (cp == '.' || cs == cp) {
                    r = isMatch(s, si + 1, p, pi, arr) || // 配
                            isMatch(s, si, p, pi + 2, arr); // 不配
                } else { // 不能匹配
                    r = isMatch(s, si, p, pi + 2, arr);
                }
            } else { // 这是一个普通字符
                if (cp == '.' || cs == cp) r = isMatch(s, si + 1, p, pi + 1, arr);
                else r = false;
            }
            arr[si][pi] = r ? 1 : -1;
        }
        return r;
    }

    public static void main(String[] args) {
        P10_RegularExpressionMatching p10 = new P10_RegularExpressionMatching();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String p = "a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*";

        System.out.println(p10.isMatch(s, p));
    }
}
