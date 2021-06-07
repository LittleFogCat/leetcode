package top.littlefogcat.leetcode;

public class P762_PrimeNumberOfSetBitsInBinaryRepresentation {
    /**
     * Prime: 质数
     */
    class Solution {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19};

        public int countPrimeSetBits(int L, int R) {
            int count = 0;
            for (int i = L; i <= R; i++) {
                if (isPrime(numOfBits(i))) {
                    count++;
                }
            }
            return count;
        }

        private boolean isPrime(int aInt) {
            if (aInt % 2 == 0 && aInt > 2) {
                return false;
            }
            for (int prime : primes) {
                if (prime == aInt) {
                    return true;
                }
            }
            return false;
        }

        private int numOfBits(int aInt) {
            int bits = 0;
            while (aInt != 0) {
                bits += aInt & 1;
                aInt >>= 1;
            }
            return bits;
        }
    }

    static class Solution2 {
        static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19};

        public int countPrimeSetBits(int L, int R) {
            return 0;
        }

        private static boolean isPrime(int aInt) {
            if (aInt % 2 == 0 && aInt > 2) {
                return false;
            }
            for (int prime : primes) {
                if (prime == aInt) {
                    return true;
                }
            }
            return false;
        }

        private static int numOfBits(int aInt) {
            int bits = 0;
            while (aInt != 0) {
                bits += aInt & 1;
                aInt >>= 1;
            }
            return bits;
        }

        public static int countI(int i) {
            if (i <= 2) {
                return 0;
            }
            int i_1 = countI(i - 1);
            return isPrime(numOfBits(i)) ? i_1 + 1 : i_1;
        }
    }


    public static void main(String[] args) {
//        for (int i = 0; i < 500; i++) {
            System.out.println(Solution2.countI(1000000));
//        }
    }
}
