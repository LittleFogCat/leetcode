package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P763_PartitionLabels {
    class Solution {
        public List<Integer> partitionLabels(String S) {

            List<Integer> list = new ArrayList<>();

            int length = S.length();
            int cur = 0;

            while (cur < length) {
                String s = end(S, cur);
                list.add(s.length());
                cur += s.length();
            }

            return list;
        }

        /**
         * 传入字符串和开始序号
         * <p>
         * 返回子字符串
         */
        public String end(String S, int start) {
            List<Character> done = new ArrayList<>();
            int end = S.lastIndexOf(S.charAt(start));
            // 从start开始遍历这个字符串到end，如果其中的字符出现在start到end之外的，那么扩充end到其位置
            for (int i = start; i < end; i++) {
                char c = S.charAt(i);// 遍历到的字符
                if (done.contains(c)) {
                    continue; // 如果这个字符已经遍历过了，就下一个，避免重复计算
                }
                done.add(c);
                int cInd = S.lastIndexOf(c);
                if (cInd > end) {
                    end = cInd;
                }
            }

            return S.substring(start, end + 1);
        }
    }
}
