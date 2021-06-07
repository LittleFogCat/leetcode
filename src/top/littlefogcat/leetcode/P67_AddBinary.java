package top.littlefogcat.leetcode;

public class P67_AddBinary {
    public String addBinary(String a, String b) {
        if ("0".equals(a)) return b;
        if ("0".equals(b)) return a;
        // 保证a长于b
        if (a.length() < b.length()) {
            String t = a;
            a = b;
            b = t;
        }
        StringBuilder s = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        for (; j >= 0; i--, j--) {
            char numA = a.charAt(i);
            char numB = b.charAt(j);
            if (numA == '0' && numB == '0') {
                s.append(carry);
                carry = 0;
            } else if (numA == '1' && numB == '1') {
                s.append(carry);
                carry = 1;
            } else {
                s.append(1 - carry);
            }
        }
        for (; i >= 0; i--) {
            if (carry == 0) s.append(a.charAt(i));
            else {
                char numA = a.charAt(i);
                if (numA == '0') {
                    s.append(1);
                    carry = 0;
                } else {
                    s.append(0);
                }
            }
        }
        if (carry == 1) s.append(1);
        return s.reverse().toString();
    }
}
