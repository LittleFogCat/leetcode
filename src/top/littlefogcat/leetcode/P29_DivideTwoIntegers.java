package top.littlefogcat.leetcode;

public class P29_DivideTwoIntegers {

    /**
     * 思路：如何用二进制表达数？
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; // 溢出
        boolean neg = dividend > 0 ^ divisor > 0;
        if (dividend > 0) dividend = -dividend;
        if (divisor > 0) divisor = -divisor;
        int shang = 0; // 商
        while (dividend <= divisor) {
            // dividend = divisor + 2 * divisor + 4 * divisor + ... + 2^x * divisor
            //          = divisor + divisor << 1 + divisor << 2 + ... + divisor << x
            int pow = 0;
            int result = divisor;

            int threshold = dividend > -4 ? 0 : dividend >> 2;
            while (result >= threshold) {
                pow++;
                result = result << 1; // divisor << x
            }
            dividend -= result;
            shang += 1 << pow;
        }
        return neg ? -shang : shang;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1 >>> 1));
        System.out.println(Integer.toBinaryString(-1));
    }
}
