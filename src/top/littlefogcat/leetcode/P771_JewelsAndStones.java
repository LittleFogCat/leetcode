package top.littlefogcat.leetcode;

import java.util.HashSet;
import java.util.Set;

public class P771_JewelsAndStones {

    public P771_JewelsAndStones(String J, String S) {
        int result = new Solution1().numJewelsInStones(J, S);
        System.out.println(result);
    }

    class Solution1 {
        public int numJewelsInStones(String J, String S) {
            int ret = 0;
            for (char c : J.toCharArray()) {
                for (char c1 : S.toCharArray()) {
                    if (c == c1) {
                        ret++;
                    }
                }
            }
            return ret;
        }
    }

    class Solution2 {
        private Set<Character> jewelSet = new HashSet<>();

        public int numJewelsInStones(String J, String S) {
            for (char c : J.toCharArray()) {
                jewelSet.add(c);
            }
            int ret = 0;
            for (char c : S.toCharArray()) {
                if (jewelSet.contains(c)) {
                    ret++;
                }
            }
            return ret;
        }
    }
}
