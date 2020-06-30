package top.littlefogcat.leetcode;

public class P693_BinaryNumberWithAlternatingBits {
    class Solution {
        public boolean hasAlternatingBits(int n) {
            int flag = n & 1;
            while (n != 0) {
                n >>= 1;
                int newFlag = n & 1;
                if (newFlag == flag) {
                    return false;
                }
                flag = newFlag;
            }
            return true;
        }
    }
}
