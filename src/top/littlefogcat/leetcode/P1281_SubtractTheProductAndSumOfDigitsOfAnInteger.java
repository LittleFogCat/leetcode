package top.littlefogcat.leetcode;

public class P1281_SubtractTheProductAndSumOfDigitsOfAnInteger {
    public int subtractProductAndSum(int n) {
        int sum = 0;
        int mul = 1;
        while (n > 0) {
            int last = n % 10;
            sum += last;
            mul *= last;
            n /= 10;
        }
        return mul - sum;
    }
}
