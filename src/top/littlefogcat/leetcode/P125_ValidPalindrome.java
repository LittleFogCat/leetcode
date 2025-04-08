package top.littlefogcat.leetcode;

public class P125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < s.length() && j >= 0 && i < j; ) {
            if (!isValidChar(chars[i])) i++;
            else if (!isValidChar(chars[j])) j--;
            else {
                if (isDigit(chars[i]) != isDigit(chars[j])) return false;
                if (toLowercase(chars[i]) != toLowercase(chars[j])) return false;
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean isValidChar(char c) {
        return isDigit(c) || c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private char toLowercase(char c) {
        if (c >= 'A' && c <= 'Z') return (char) (c + 'a' - 'A');
        return c;
    }
}
