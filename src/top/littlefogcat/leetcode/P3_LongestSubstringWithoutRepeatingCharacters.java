package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println("longgest = " + solution.lengthOfLongestSubstring("tmmzuxt"));
    }

    /**
     * map+双指针
     */
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) return 0;
            char[] string = s.toCharArray();
            int[] charMap = new int[128];
            Arrays.fill(charMap, -1);
            int max = 1;

            for (int i = 0, start = 0; i < string.length; i++) {
                char ch = string[i]; // 当前串 -- s[start, i]
                if (charMap[ch] != -1) { // 如果字符出现过
                    start = Math.max(start, charMap[ch] + 1); // 更新当前串起点
                }
                max = Math.max(max, i - start + 1);
                charMap[ch] = i;
                System.out.println(max);
            }

            return max;
        }

    }
}
