package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 */
@SuppressWarnings("StatementWithEmptyBody")
public class P44_WildcardMatching {
    private int[][] results;

    public boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        if (s.length() == 0 /* s为空p不为空 */) {
            for (char c : p.toCharArray())
                if (c != '*') return false; // 只有当p全为*才能匹配
        }
        results = new int[s.length()][p.length()]; // 动态规划保存结果，1=true，-1=false，0=未执行
        return isMatch(s, 0, p, 0);
    }

    /**
     * @param s      原字符串
     * @param sStart 原字符串匹配起点
     * @param p      通配字符串
     * @param pStart 通配字符串匹配起点
     * @return s、p分别从sStart、pStart开始的子字符串是否匹配
     */
    private boolean isMatch(String s, int sStart, String p, int pStart) {
        if (sStart == s.length() && pStart == p.length()) return true; // s，p正好同时匹配完
        if (pStart == p.length()) return false; // p已经匹配完了，s还有剩余
        if (sStart == s.length()) { // s已经匹配完了，p必须全为*才返回true
            for (int i = pStart; i < p.length(); i++) {
                if (p.charAt(i) != '*') return false;
            }
            return true;
        }

        if (results[sStart][pStart] != 0) return results[sStart][pStart] == 1;
        boolean result; // 保存结果
        char cs = s.charAt(sStart); // 原字符串当前的字符
        char cp = p.charAt(pStart); // 通配字符串当前的字符
        if (cs == cp || cp == '?') {
            result = isMatch(s, sStart + 1, p, pStart + 1);
        } else if (cp == '*') {
            boolean include = isMatch(s, sStart + 1, p, pStart); // cs被*通配
            boolean notInclude = isMatch(s, sStart, p, pStart + 1); // cs不被*通配
            result = include || notInclude;
        } else { // cp不是通配符，且cs!=cp
            result = false;
        }

        results[sStart][pStart] = result ? 1 : -1;
        return result;
    }


    //-------------------------------------分割线---------------------------------------------

    /**
     * 其实p里面的*根本不重要！直接无视掉就行！
     * <p>
     * 将p里面用*隔开的子字符串当做关键词，再在s中查找即可！
     */
    public boolean isMatch1(String s, String p) {
        if (p.length() == 0) return s.length() == 0;

        boolean allStar = true; // p全是*
        for (int i = 0; i < p.length() && (allStar = p.charAt(i) == '*'); i++) ;
        if (allStar) return true;
        if (s.length() == 0) return false;

        int starIndex = -1;
        int pLen = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                if (starIndex == -1) starIndex = i;
            } else pLen++;
        }
        if (pLen > s.length()) return false;

        // 1. p里面没有*，全部匹配
        if (starIndex == -1) {
            return match(s, 0, s.length(), p, 0, p.length());
        }

        // 2. p中有*
        List<String> words = new ArrayList<>(); // 关键词
        for (int i = 0; i < p.length(); ) {
            if (starIndex == -1) {
                words.add(p.substring(i));
                break;
            }
            if (i == starIndex) { // p[i] == '*'
                i++;
            } else {
                String word = p.substring(i, starIndex);
                words.add(word);
                i = starIndex + 1;
            }
            starIndex = p.indexOf('*', i);
        }

        int first = 0, last = words.size();
        int start = 0, lastStart = -1;
        // 第一项绝对匹配
        if (!p.startsWith("*")) {
            String firstWord = words.get(0);
            int len = firstWord.length();
            if (!match(s, 0, len, firstWord, 0, len)) {
                return false;
            }
            first++;
            start = len;
        }

        // 最后一项绝对匹配
        if (!p.endsWith("*")) {
            String lastWord = words.get(words.size() - 1);
            int len = lastWord.length();
            if (start + len > s.length()) return false;
            if (!match(s, s.length() - len, s.length(), lastWord, 0, len)) {
                return false;
            }
            last--;
            lastStart = s.length() - len;
        }

        // 第二项到倒数第二项
        for (int i = first; i < last; i++) {
            String word = words.get(i);
            int index = indexOf(s, word, start);
            if (index == -1) return false;
            start = index + word.length();
            if (lastStart != -1 && start > lastStart) return false;
        }

        return true;
    }

    /**
     * 子字符串 s[ss..se) 和 p[ps..pe) 是否匹配。
     * 这两个子字符串里面必不可有*。
     */
    private boolean match(String s, int ss, int se, String p, int ps, int pe) {
        if (se - ss != pe - ps) return false;
        for (int si = ss, pi = ps; si < se; si++, pi++) {
            char cs = s.charAt(si);
            char cp = p.charAt(pi);
            if (cp != '?' && cp != cs) return false; // 匹配不上
        }
        return true;
    }

    /**
     * 找到通配字符串p在原字符串s中的序号，必不可有*
     *
     * @param start s中的起始序号
     * @return 如果没有找到，返回-1
     */
    private int indexOf(String s, String p, int start) {
        if (start + p.length() > s.length()) return -1; // 长度超过
        int first = 0, end = p.length() - 1; // p中第一个不是?的字符
        for (; first < p.length() && p.charAt(first) == '?'; first++) ;
        if (first == p.length()) return start; // p全是?
        for (; end > first && p.charAt(end) == '?'; end--) ;
        // 把p开头和结尾的?去掉，只用找中间的部分p[firs..end]
//        System.out.println("real p: " + p.substring(first, end + 1));
        int realStart = start + first;
        int realEnd = s.length() - (p.length() - end);
        // 实际查找部分s[realStart..realEnd]
//        System.out.println("real s: " + s.substring(realStart, realEnd + 1));
        int lastStart = realEnd - (end - first); // s循环的最后一项
        char firstChar = p.charAt(first);
        for (int i = realStart; i <= lastStart; i++) {
            if (s.charAt(i) != firstChar) continue; // 匹配第一个字符
            if (i > lastStart) break; // 剩下的长度不足以匹配了
            int j, k;
            for (j = i + 1, k = first + 1; k <= end && (p.charAt(k) == '?' || s.charAt(j) == p.charAt(k)); j++, k++) ;
            if (k == end + 1) { // 匹配到了
                return i - first;
            }
        }

        return -1;
    }

    //--------------------------------------分割线------------------------------------------

    public boolean isMatch2(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();

        int i = 0, j = 0; // 双指针
        int endS = sChars.length - 1, endP = pChars.length - 1;
        // 匹配开头
        for (; i < sChars.length && j < pChars.length; i++, j++) {
            if (pChars[j] == '*') break; // 找到第一个*
            if (pChars[j] != '?' && pChars[j] != sChars[i]) return false; // 不匹配，返回false
        }
        // 匹配结尾
        for (; endS >= i && endP >= j; endS--, endP--) {
            if (pChars[endP] == '*') break;
            if (sChars[endS] != pChars[endP] && pChars[endP] != '?') return false;
        }
        // s[i..endS]、p[j..endP]开始匹配
        if (endP < j) return endS < i;
        if (endS < i) return isAllAsterisk(pChars, j, endP);
        loop1:
        while (i <= endS && j <= endP) {
            // 匹配成功的情况，1：p只剩*了；2：s和p都匹配到结尾了
            while (j <= endP && pChars[j] == '*') j++; // 跳过p[j]为*的字符
            if (j > endP) return true; // p的后面都是*了，匹配上（情况1）
            // 开始匹配
            int i1 = i, j1 = j;
            for (; i1 <= endS && j1 <= endP; i1++, j1++) { // i, j
                if (pChars[j1] == '*') { // 到目前为止都匹配正确，遇到*那么跳过找后面的
                    if (j1 == endP) return true; // 前面匹配完成，p只剩一个*
                    i = i1;
                    j = j1 + 1;
                    continue loop1;
                } else if (sChars[i1] != pChars[j1] && pChars[j1] != '?') { // 不匹配，那么从s[i+1]做起点继续匹配
                    i++;
                    continue loop1;
                }
                // 当前字符匹配上了，匹配下一个字符
            } // 全匹配上了，并且到了s或者p的结尾了
            if (j1 > endP) /* p匹配完毕，那么必须s也同时完毕才满足 */ return i1 > endS; // 完毕（情况2）
            if (i1 > endS) /* s匹配完毕，p没有匹配完毕，那么p剩下的必须全是* */ return isAllAsterisk(pChars, j1, endP);
        }
        return false; // 匹配到结尾，并且两种成功的情况都不满足
    }

    private boolean isAllAsterisk(char[] p, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (p[i] != '*') return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        P44_WildcardMatching p44 = new P44_WildcardMatching();
//        String s = "abcdabcd";
//        String p0 = "?c?a?";
//        String p1 = "c?a?";
//        String p2 = "?c?a";
//        String p3 = "c?a";
//        System.out.println(p44.indexOf(s, p0, 0));
//        System.out.println(p44.indexOf(s, p1, 0));
//        System.out.println(p44.indexOf(s, p2, 0));
//        System.out.println(p44.indexOf(s, p3, 0));

//        String p4 = "b*a";
//        String p5 = "b*??da";
//        System.out.println(p44.isMatch1(s, p4));
//        System.out.println(p44.isMatch1(s, p5));
//        testMatch("c", "*?*");
//        testMatch("c", "?*?");
//        testMatch("c", "?");
//        testMatch("c", "*");
//        testMatch("c", "**");
//        testMatch("c", "?*");
//        testMatch("c", "*?");
//        testMatch("c", "??");
//        testMatch("ab", "*?*?*");
//        testMatch("ab", "?*****?");
//        testMatch("ab", "a?*****?");
//        testMatch("ab", "*?*?");
//        testMatch("ab", "?*?*");
//        testMatch("ab", "*??*");
        testMatch("adceb", "*a*b", true);
        testMatch("mississippi", "m??*ss*?i*pi", false);
        testMatch("aab", "*?*aa*", false);
        testMatch("aa", "**a", true);
        testMatch("aaa", "**aa", true);
        testMatch("aaa", "**a", true);
    }

    private static void testMatch(String s, String p, boolean expect) {
        P44_WildcardMatching p44 = new P44_WildcardMatching();
        System.out.println("s = " + s + ", p = " + p + ", " + p44.isMatch2(s, p) + ", " + expect);
    }
}
