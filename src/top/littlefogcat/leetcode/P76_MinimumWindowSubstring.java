package top.littlefogcat.leetcode;

public class P76_MinimumWindowSubstring {
    /**
     * 思路：Map + 双指针。
     * 1. Map统计字符出现次数。
     * 2. 使用双指针在字符串s中截取子字符串（窗口）。根据子字符串是否包含t中所有字符来进行扩大或缩小窗口。
     * 当截取的字符串覆盖了t中所有字符，则缩小窗口；反之则扩大窗口。
     * 其中扩大的方式为终点右移，缩小方式为起始点右移。
     */
    public String minWindow(String s, String t) {
        int[] charCountT = new int[128]; // t中字符的数量
        int[] charCountS = new int[128]; // 双指针截取的s子字符串中字符的数量
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            charCountT[c]++;
        }

        // 找出第一个覆盖t的子字符串，如果没有则返回空字符串
        int covered = 0;
        int i = 0, j = 0;
        for (; j < s.length(); j++) {
            char c = s.charAt(j);
            if (charCountS[c] < charCountT[c])
                covered++; // 覆盖+1
            charCountS[c]++;
            if (covered == t.length()) break; // 已经全部覆盖
        }
        if (covered != t.length()) return ""; // 没有全部覆盖

        int minI = i, minJ = j;
        boolean isAllCovered = true;
        char needCover = 0; // 缩小窗口时丢弃导致没有完全覆盖的字符
        while (true) {
            if (isAllCovered) {
//                System.out.println(s.substring(i, j + 1));
                if (j - i < minJ - minI) {
                    minI = i;
                    minJ = j;
                }
                // 全覆盖，起始点右移
                char removed = s.charAt(i);
                charCountS[removed]--;
                if (charCountS[removed] < charCountT[removed]) {
                    // 如果当前丢弃的字符导致
                    isAllCovered = false;
                    needCover = removed;
                }
                i++;
            } else {
                // 未全覆盖，终点右移
                j++;
                if (j >= s.length()) break;
                char added = s.charAt(j);
                charCountS[added]++;
                if (added == needCover) {
                    isAllCovered = true;
                }
            }
        }
        return s.substring(minI, minJ + 1);
    }
}
