package top.littlefogcat.leetcode;

//13
public class P9_PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int y = x;
        int z = 0;
        while (y != 0) {
            z = z * 10 + (y % 10);
            y /= 10;
        }

        return z == x;
    }

}
