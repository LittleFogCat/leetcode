package top.littlefogcat.leetcode;

/*
 * 21:20
 */
public class P6_ZigZagConversion {
    public String convert(String s, int numRows) {
        if (s.length() < 2 || numRows == 1) return s;
        char[] result = new char[s.length()];
        for (int i = 0, row = 0, j = 0; j < result.length && row < numRows; ) {
            if (i >= s.length()) {
                i = ++row;
            }
            result[j++] = s.charAt(i);
            int mod = numRows - 1;
            int nextCorner = i - i % mod + mod;
            i = 2 * nextCorner - i;
        }
        return new String(result);
    }


    public static void main(String[] args) {
        P6_ZigZagConversion p6 = new P6_ZigZagConversion();
        String s = "ABCD";
        int row = 3;
        String z = p6.convert(s, row);
        System.out.println(z);
    }
}
