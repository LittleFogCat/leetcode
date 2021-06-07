package top.littlefogcat.leetcode;

public class P231_PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if(n < 1) return false;
        while(true) {
            if(n == 1) return true;
            if((n & 1) == 1) return false;
            n = n >> 1;
        }
    }
}
