package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P5_LongestPalindromicSubstring {

    class Solution {
        public String longestPalindrome(String s) {
            if (s.length() < 2) return s;
            char[] string = s.toCharArray();
            int[] max = new int[2];

            int left, right;
            for (int i = 0; i < string.length; i = right + 1) {
                left = right = i;
                // 如果回文中心是重复的字符，那么就把right移到最后一个重复的字符上
                // 这样可以保证string[left..right]都相等
                while (right < string.length - 1 && string[right + 1] == string[right]) {
                    right++;
                }
                checkPalindrome(string, left, right, max);
            }

            return s.substring(max[0], max[1] + 1);
        }

        private void checkPalindrome(char[] string, int left, int right, int[] max) {
            for (; left >= 0 && right < string.length; left--, right++) {
                // 如果左右相等，则继续扩散，反之停止
                if (string[left] != string[right]) return;
                if (right - left > max[1] - max[0]) {
                    max[0] = left;
                    max[1] = right;
                }
            }
        }

    }

    /**
     * Manacher算法
     */
    class Solution1 {

        public String longestPalindrome(String s) {
            if (s.length() < 2) return s;
            char[] str = new char[s.length() * 2 + 1];
            int[] len = new int[str.length];
            Arrays.fill(len, 1);
            int mid = 0; // 当前最大回文的中心

            str[0] = '$';
            for (int i = 0; i < s.length(); i++) {
                str[i * 2 + 1] = s.charAt(i);
                str[i * 2 + 2] = '$';
            }

            len[0] = 1;
            int left, right;
            for (int i = 1; i < str.length; i++) { // 以i为中心
                int P = mid + len[mid] - 1; // 当前最大回文最后一位
                if (i > P) { // str[i] 不包括在当前最大回文字符串内，计算len[i]
                    for (left = i - 1, right = i + 1; left >= 0 && right <= str.length - 1 && str[left] == str[right]; left--, right++) {
                        len[i]++;
                    }
                } else { // str[i] 包含在当前的最大回文字符串内
                    int j = mid * 2 - i; // 以mid为中心，i的对称点
                    if (j < 0) break;
                    len[i] = len[j];
                    if (i + len[j] < P) continue; // len[i] = len[j]
                    for (right = P + 1, left = i * 2 - right; left >= 0 && right < str.length; left--, right++) {
                        if (str[left] != str[right]) break;
                        len[i]++;
                    }
                }
                if (len[i] > len[mid]) mid = i;
            }
            left = (mid - len[mid] + 2) / 2;
            right = (mid + len[mid] - 2) / 2;
            return s.substring(left, right + 1);
        }
    }


    public static void main(String[] args) {
        P5_LongestPalindromicSubstring p5 = new P5_LongestPalindromicSubstring();
        Solution solution = p5.new Solution();
        Solution1 solution1 = p5.new Solution1();

        String s = "wrtyipothjkjqeyioyuooiioyeuutryqtrtfgbvnmfnkglksiohfyuftgikjpkluopykoistfafhjyliuolytga";

        test(solution, s);
        test(solution1, s);
    }

    public static void test(Object solution, String s) {
        if (solution instanceof Solution) {
            String longest = ((Solution) solution).longestPalindrome(s);
            System.out.println("Solution: s = " + s + ", longest = " + longest);
        }
        if (solution instanceof Solution1) {
            String longest = ((Solution1) solution).longestPalindrome(s);
            System.out.println("Solution1: s = " + s + ", longest = " + longest);
        }
    }
}
