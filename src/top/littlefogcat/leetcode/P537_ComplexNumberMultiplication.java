package top.littlefogcat.leetcode;

public class P537_ComplexNumberMultiplication {
    class Solution {
        public String complexNumberMultiply(String a, String b) {
            String[] aa = a.split("\\+");
            int a1 = Integer.valueOf(aa[0]);
            int a2 = Integer.valueOf(aa[1].substring(0, aa[1].length() - 1));
            String[] bb = b.split("\\+");
            int b1 = Integer.valueOf(bb[0]);
            int b2 = Integer.valueOf(bb[1].substring(0, bb[1].length() - 1));

            return (a1 * b1 - a2 * b2) + "+" + (a1 * b2 + a2 * b1) + "i";
        }
    }
}
