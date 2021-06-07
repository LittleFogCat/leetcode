package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P961_N_RepeatedElementInSize2NArray {
    class Solution {
        public int repeatedNTimes(int[] A) {
            int len = A.length;
            int[] map = new int[10000];

            for (int ai : A) {
                map[ai]++;
            }

            for (int i = 0; i < map.length; i++) {
                if (map[i] == len / 2) {
                    return i;
                }
            }

            return 0;
        }
    }

    class Solution1 {
        int r;

        public int repeatedNTimes(int[] A) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int ai : A) {
                map.put(ai, map.getOrDefault(ai, 0) + 1);
            }
            map.forEach((num, count) -> {
                if (count == A.length / 2) {
                    r = num;
                }
            });
            return r;
        }
    }

    class Solution2 {
        int r;
    }
}
