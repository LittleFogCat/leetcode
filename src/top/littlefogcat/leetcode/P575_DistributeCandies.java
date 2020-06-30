package top.littlefogcat.leetcode;

import java.util.HashSet;
import java.util.Set;

public class P575_DistributeCandies {
    class Solution1 {
        private final Set<Integer> intSet = new HashSet<>();

        public int distributeCandies(int[] candies) {
            int current = 0;
            int max = candies.length / 2;
            for (int i : candies) {
                if (!intSet.contains(i)) {
                    intSet.add(i);
                    current++;
                    if (current >= max) {
                        break;
                    }
                }
            }
            return current;
        }
    }

    class Solution2 {
        /**
         * a map/set construct with a array
         * <p>
         * -100000 -> map[0]
         * -99999 -> map[1]
         * ...
         * x -> map[x + 100000]
         * ...
         * 100000 -> map[200000]
         */
        private boolean[] map = new boolean[200001];

        public int distributeCandies(int[] candies) {
            int current = 0;
            int max = candies.length / 2;
            for (int i : candies) {
                int realIndex = i + 100000;
                if (!map[realIndex] ) {
                    map[realIndex] = true;
                    current++;
                    if (current >= max) {
                        break;
                    }
                }
            }
            return current;
        }
    }
}
