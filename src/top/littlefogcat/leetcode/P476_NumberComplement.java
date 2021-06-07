package top.littlefogcat.leetcode;

public class P476_NumberComplement {
    class Solution {
        public int findComplement(int num) {
            if (num == 0) {
                return 1;
            }
            int result = 0;
            int pow = 1;
            while (num > 0) {
                result += (1 - num % 2) * pow;
                num /= 2;
                pow *= 2;
            }
            return result;
        }
    }

    class Solution2 {
        public int findComplement(int num) {
            int bits = 0;
            int n = num;
            while (n > 0) {
                n >>= 1;
                bits++;
            }
            return ~num & (1 << bits) - 1;
        }
    }
}
