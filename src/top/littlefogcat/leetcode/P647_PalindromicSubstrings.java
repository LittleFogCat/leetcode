package top.littlefogcat.leetcode;

public class P647_PalindromicSubstrings {
    /**
     * 暴力且低效
     */
    class Solution {
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            } else if (s.length() == 1) {
                return 1;
            }
            int count = 0;
            int len = s.length();
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j <= len; j++) {
                    String sub = s.substring(i, j);
                    if (isPalindromic(sub)) {
                        count++;
                    }
                }
            }

            return count;
        }

        public boolean isPalindromic(String s) {
            char[] toCharArr = s.toCharArray();
            int len = toCharArr.length;
            int end = len / 2;

            for (int i = 0; i < end; i++) {
                if (toCharArr[i] != toCharArr[len - 1 - i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
