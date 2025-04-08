package top.littlefogcat.leetcode;

import java.util.List;

/**
 * 思路：DP
 */
public class P139_WordBreak {
    private Boolean[] dp;

    public boolean wordBreak(String s, List<String> wordDict) {
        dp = new Boolean[s.length()];
        return canBeBreak(s.toCharArray(), 0, wordDict);
    }

    private boolean canBeBreak(char[] s, int start, List<String> wordDict) {
        if (start == s.length) return true;
        if (dp[start] != null) return dp[start];
        for (String word : wordDict) {
            if (startsWith(s, start, word)) {
                if (canBeBreak(s, start + word.length(), wordDict)) {
                    dp[start] = true;
                    break;
                }
            }
        }
        if (dp[start] == null) dp[start] = false;
        return dp[start];
    }

    private boolean startsWith(char[] s, int start, String word) {
        if (s.length - start < word.length()) return false;
        for (int i = 0; i < word.length(); i++) {
            if (s[start + i] != word.charAt(i)) return false;
        }
        return true;
    }
}
