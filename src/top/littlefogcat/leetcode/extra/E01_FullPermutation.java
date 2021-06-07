package top.littlefogcat.leetcode.extra;

import java.util.HashSet;
import java.util.Set;

/**
 * 求一个字符串的全排列
 */
public class E01_FullPermutation {

    public class Solution {

        private Set<String> mSet = new HashSet<>();
        private String mOrigin;

        public Solution(String origin) {
            mOrigin = origin;
        }

        public Set<String> allP() {
            allP("", mOrigin);
            return mSet;
        }

        public void allP(String before, String left) {
            if (left.length() == 1) {
                mSet.add(before + left);
                return;
            }
            for (int i = 0; i < left.length(); i++) {
                char c = left.charAt(i);
                String newLeft = left.substring(0, i) + left.substring(i + 1, left.length());
                String newBefore = before + c;
                allP(newBefore, newLeft);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new E01_FullPermutation().new Solution("abc123").allP());
    }

}
