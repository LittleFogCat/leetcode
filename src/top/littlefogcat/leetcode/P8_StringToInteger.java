package top.littlefogcat.leetcode;

public class P8_StringToInteger {
    public int myAtoi(String str) {
        char[] s = str.toCharArray();
        long num = 0;
        boolean negFlag = false;
        boolean started = false;

        for (char c : s) {
            if (!started && c != ' ') {
                if (c == '-') {
                    negFlag = true;
                } else if (c >= '0' && c <= '9') {
                    num = c - '0';
                } else if (c != '+') {
                    return 0;
                }
                started = true;
            } else {
                if (c > '9' || c < '0') {
                    break;
                }
                num = num * 10 + (c - '0');
                if (num > Integer.MAX_VALUE + 1L) break;
            }
        }
        num = negFlag ? -num : num;
        return (int) Math.max(Math.min(num, Integer.MAX_VALUE), Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        P8_StringToInteger p8 = new P8_StringToInteger();
        System.out.println(p8.myAtoi("9223372036854775808"));
    }
}
