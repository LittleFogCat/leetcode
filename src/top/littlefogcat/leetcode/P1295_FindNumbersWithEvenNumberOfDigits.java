package top.littlefogcat.leetcode;

public class P1295_FindNumbersWithEvenNumberOfDigits {
    public int findNumbers(int[] nums) {
        int c = 0;
        for (int num : nums) {
//            if ((int) (Math.log10(num) + 1) % 2 == 0) {
            if (num >= 10 && num < 100 || num >= 1000 && num < 10000 || num == 100000) {
                c++;
            }
        }
        return c;
    }
}
