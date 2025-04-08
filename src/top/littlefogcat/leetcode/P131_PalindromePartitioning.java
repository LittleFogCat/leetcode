package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：回溯
 * 可能的优化点：减少重复计算
 */
public class P131_PalindromePartitioning {
    Boolean[][] palindrome;

    public List<List<String>> partition(String s) {
        palindrome = new Boolean[s.length()][s.length()];
        return divide(new ArrayList<>(), new ArrayList<>(), s.toCharArray(), 0);
    }

    private List<List<String>> divide(List<List<String>> ans, List<String> splits, char[] s, int start) {
        for (int end = start; end < s.length; end++) {
            if (!isPalindrome(s, start, end)) continue;
            splits.add(new String(s, start, end - start + 1));
            if (end == s.length - 1) {
                ans.add(new ArrayList<>(splits));
            } else {
                divide(ans, splits, s, end + 1);
            }
            splits.remove(splits.size() - 1); // 回溯
        }
        return ans;
    }

    private boolean isPalindrome(char[] s, int start, int end) {
        if (palindrome[start][end] != null) return palindrome[start][end];
        do {
            if (s[end] != s[start]) return false;
        } while (end-- > start++);
        return true;
    }
}
