package top.littlefogcat.leetcode;

public class P14_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        int len = Integer.MAX_VALUE;
        for (String str : strs) {
            if (str.length() < len) len = str.length();
        }
        if (len == 0) return "";
        char[] s = new char[len];
        int i; // 字符串长度
        out:
        for (i = 0; i < len; i++) {
            char chI = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (chI != strs[j].charAt(i)) {
                    break out;
                }
            }
            s[i] = chI;
        }

        return new String(s, 0, i);
    }


    public static void main(String[] args) {
        P14_LongestCommonPrefix p14 = new P14_LongestCommonPrefix();
        p14.longestCommonPrefix(new String[]{});
    }
}
