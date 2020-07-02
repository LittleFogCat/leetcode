package top.littlefogcat.leetcode;

// 40
public class P10_RegularExpressionMatching {
//    public boolean isMatch(String s, String p) {
//        int star = p.indexOf('*');
//        int dot = p.indexOf('.');
//        if (star == -1 && dot == -1) return s.equals(p);
//
//        if (p.contains(".*")) {
//            return isMatch0(s, p);
//        }
//        return startWith(s, 0, s.length(), p) == s.length();
//    }
//
//    public boolean isMatch0(String s, String p) {
//        String regex = "\\.\\*";
//        String[] p1 = p.split("\\.\\*");
//        boolean isAllDotStar = true;
//        for (String ip : p1) {
//            isAllDotStar = isAllDotStar && ip.length() == 0;
//        }
//        if (isAllDotStar) return true;
//        if (!p.startsWith(regex) && startWith(s, 0, s.length(), p1[0]) == -1 || // s必须以p[0]开头
//                !p.endsWith(regex) && endWith(s, 0, s.length(), p1[p1.length - 1]) == -1) { // 必须以结尾
//            return false;
//        }
//        int si = 0;
//        for (String pstr : p1) {
//            // 每个pstr在s中出现
//            if (pstr.isEmpty()) continue;
//            si = contains(s, pstr, si, s.length());
//            if (si == -1) return false;
//        }
//        return true;
//    }
//
//    /**
//     * 判断s是否以p开头，p中不包含.*
//     *
//     * @param s     原字符串
//     * @param start 查找起始位置
//     * @param end   查找结束位置，不包含
//     * @param p     查找字符串
//     * @return s不是以p开头，返回-1；否则返回p在s中的最后一位的序号
//     */
//    public int startWith(String s, int start, int end, String p) {
//        int i, j;
//        for (i = start, j = 0; i < end && j < p.length(); ) {
//            char cs = s.charAt(i);
//            char cp = p.charAt(j);
//            if (cp == '.') {
//                i++;
//                j++;
//            } else if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
//                // p[i] == a*
//                int least = 0; // s[i]起最少需要重复多少个当前字符
//                int actual = 0; // s[i]起实际重复了多少个当前字符
//                j += 2;
//                for (; j < p.length(); ) {
//                    if (p.charAt(j) == cp) {
//                        least++;
//                        j++;
//                    } else if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
//
//                    }
//                }
//                for (; i < end && s.charAt(i) == cp; i++) actual++;
//                if (actual < least) return -1;
//            } else {
//                // 正常字符
//                if (cs != cp) return -1;
//                i++;
//                j++;
//            }
//
//        }
//        return j == p.length() ? i : -1;
//    }
//
//    /**
//     * 判断s是否以p结尾
//     *
//     * @return 如果s不以p结尾，返回-1；否则返回p在s中的起始位置
//     */
//    public int endWith(String s, int start, int end, String p) {
//        int i, j;
//        for (i = end - 1, j = p.length() - 1; i >= start && j >= 0; ) {
//            char cs = s.charAt(i);
//            char cp = p.charAt(j);
//            if (cp == '.') {
//                i--;
//                j--;
//            } else if (cp == '*') {
//                // p[i] == a*
//                // todo 如果p以r开头，需要再判断
//                char r = p.charAt(j - 1);
//                int least = 0; // s[i]起最少需要重复多少个当前字符
//                int actual = 0; // s[i]起实际重复了多少个当前字符
//                j -= 2;
//                for (; j >= 0 && p.charAt(j) == r; j--) least++;
//                for (; i >= start && s.charAt(i) == r; i--) actual++;
//                if (actual < least) return -1;
//            } else {
//                // 正常字符
//                if (cs != cp) return -1;
//                i--;
//                j--;
//            }
//
//        }
//        return j == -1 ? i + 1 : -1;
//    }
//
//    /**
//     * 判断p是否在s中出现
//     *
//     * @param s     原字符串
//     * @param p     要查找的字符串
//     * @param start 起始查找的序号
//     * @return 如果pstr没有在s中出现，返回-1；否则返回pstr在s中最后一项的序号+1
//     */
//    public int contains(String s, String p, int start, int end) {
//        if (start >= s.length()) return -1;
////        for (int i = start, j = 0; i < end && j < p.length(); ) {
////            char cs = s.charAt(i);
////            char cp = p.charAt(j);
////            if (cp == '.') {
////                i++;
////                j++;
////            } else if (i < s.length() - 1 && s.charAt(i + 1) == '*') {
////                //  a*
////
////            } else {
////                // 正常字符
////                if (cs != cp) return -1;
////                i++;
////                j++;
////            }
////        }
//        for (int i = start; i < s.length(); i++) {
//            int r = startWith(s, i, s.length(), p);
//            if (r == -1) continue;
//            return r;
//        }
//        return -1;
//    }

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
