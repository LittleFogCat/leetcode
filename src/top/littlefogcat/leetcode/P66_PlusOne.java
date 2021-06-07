package top.littlefogcat.leetcode;

public class P66_PlusOne {
    /**
     * 如果是999...9，变为1000...0；
     * 否则把末尾的9全部变成0，第一个不是9那一位的加一；
     */
    public int[] plusOne(int[] digits) {
        int pos = digits.length - 1;
        while (pos >= 0) {
            if (digits[pos] == 9) {
                digits[pos--] = 0;
            } else {
                digits[pos]++;
                return digits;
            }
        }
        int[] r = new int[digits.length + 1];
        r[0] = 1;
        return r;
    }
}
