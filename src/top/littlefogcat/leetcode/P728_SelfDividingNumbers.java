package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P728_SelfDividingNumbers {
    class Solution {
        public List<Integer> selfDividingNumbers(int left, int right) {
            List<Integer> res = new ArrayList<>();
            for (int i = left; i <= right; i++) {
                if (isSelfDivide(i)) {
                    res.add(i);
                }
            }
            return res;
        }

        public boolean isSelfDivide(int i) {
            int i1 = i;
            while (i1 != 0) {
                int l = i1 % 10; // 末位数字
                if (l == 0) {
                    return false; // 不能为0
                }
                if (i % l != 0) {
                    return false; // 如果不能整除，返回false
                }
                i1 /= 10; // 继续下一位
            }

            return true;
        }
    }
}
