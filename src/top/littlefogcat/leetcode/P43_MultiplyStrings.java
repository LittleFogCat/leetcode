package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P43_MultiplyStrings {
    public String multiply1(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        CharSequence prod = "0";
        for (int i = 0; i < num2.length(); i++) {
            int n = num2.charAt(num2.length() - i - 1) - '0';
            CharSequence result = multiply(num1, n, i);
            prod = plus(prod, result);
        }
        prod = reverse(prod);
        return format(prod);
    }

    private CharSequence reverse(CharSequence cs) {
        StringBuilder sb = new StringBuilder(cs.length());
        for (int i = cs.length() - 1; i >= 0; i--) {
            sb.append(cs.charAt(i));
        }
        return sb;
    }

    public String format(CharSequence prod) {
        int start = 0;
        while (prod.charAt(start) == '0') {
            if (start == prod.length() - 1) break;
            start++;
        }
        return start == 0 ? prod.toString() : prod.subSequence(start, prod.length() - 1).toString();
    }

    public CharSequence multiply(CharSequence num1, int num2, int offset) {
        CharSequence prod = multiply(num1, num2);
        StringBuilder builder = new StringBuilder(num1.length() + offset + 1);
        while (offset-- > 0) {
            builder.append('0');
        }
        builder.append(prod);
        return builder;
    }

    public CharSequence multiply(CharSequence num1, int num2) {
        if (num2 == 0) return "0";
        StringBuilder builder = new StringBuilder(num1.length() + 1);
        for (int i = num1.length() - 1, c = 0; i >= 0; i--) {
            int n = num1.charAt(i) - '0';
            int prod = n * num2 + c;
            if (prod > 9) {
                c = prod / 10;
                prod = prod - c * 10;
            } else c = 0;
            builder.append(prod);
            if (i == 0 && c != 0) {
                builder.append(c);
            }
        }
        return builder;
    }

    public CharSequence plus(CharSequence num1, CharSequence num2) {
//        System.out.println(num1 + " + " + num2);
        // 保证num1长度大于等于num2
        if (num2.length() > num1.length()) {
            CharSequence t = num1;
            num1 = num2;
            num2 = t;
        }
        StringBuilder builder = new StringBuilder(num1.length() + 1);
        for (int i = 0, c = 0; ; i++) {
            if (c == 0 && i >= num2.length()) {
                builder.append(num1, i, num1.length());
                break;
            }
            if (i == num1.length()) {
                if (c != 0) builder.append(c);
                break;
            }
            int n1 = num1.charAt(i) - '0';
            int n2 = i >= num2.length() ? 0 : num2.charAt(i) - '0';
            int sum = n1 + n2 + c;
            if (sum > 9) {
                c = 1;
                sum -= 10;
            } else c = 0;
            builder.append(sum);
        }
        return builder;
    }

    // -----------------------------------------------------

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        char[] cs1 = num1.toCharArray();
        char[] cs2 = num2.toCharArray();
        int[] result = new int[cs1.length + cs2.length];
        for (int i = 0; i < cs1.length; i++) {
            for (int j = 0; j < cs2.length; j++) {
                int n1 = cs1[i] - '0';
                int n2 = cs2[j] - '0';
                result[i + j + 1] += n1 * n2;
            }
        }
        for (int i = result.length - 1, c = 0; i >= 0; i--) {
            result[i] += c;
            c = result[i] / 10;
            if (c != 0) {
                result[i] -= c * 10;
            }
        }
//        System.out.println(Arrays.toString(result));
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (result[i] == 0) i++;
        while (i < result.length) sb.append(result[i++]);
        return sb.toString();
    }

    public static void main(String[] args) {
        P43_MultiplyStrings p = new P43_MultiplyStrings();
//        System.out.println(p.plus("1234", "4321"));
//        System.out.println(p.plus("9876", "1234"));
//        System.out.println(p.plus("19", "011"));
        System.out.println(p.multiply("9", "89"));
        System.out.println(p.multiply("2", "12"));
//
//        System.out.println(p.multiply("2", "3"));
//        System.out.println(p.multiply("9133", "0"));
//        System.out.println(p.multiply("0", "11"));
//        System.out.println(p.multiply("11111", "11111"));
//        System.out.println(p.multiply("123456789", "987654321"));
//        System.out.println(p.multiply("123456789", "1"));
//        System.out.println(p.multiply("123456789", "2"));
//        System.out.println(p.multiply("123456789", "3"));
//        System.out.println(p.multiply("123456789", "4"));
//        System.out.println(p.multiply("123456789", "5"));
//        System.out.println(p.multiply("123456789", "6"));
//        System.out.println(p.multiply("123456789", "7"));
//        System.out.println(p.multiply("123456789", "8"));
//        System.out.println(p.multiply("123456789", "9"));
    }
}
