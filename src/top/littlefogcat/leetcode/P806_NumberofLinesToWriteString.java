package top.littlefogcat.leetcode;

public class P806_NumberofLinesToWriteString {
    class Solution {
        public int[] numberOfLines(int[] widths, String S) {
            int[] result = new int[2];
            result[0] = 1;
            int tmpLen = 0;
            for (char c : S.toCharArray()) {
                int cLen = widths[c - 'a'];
                if (tmpLen + cLen > 100) {
                    result[0]++;
                    tmpLen = cLen;
                } else {
                    tmpLen += cLen;
                }
            }
            result[1] = tmpLen;
            return result;
        }
    }
}
