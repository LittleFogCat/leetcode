package top.littlefogcat.leetcode;

import java.util.*;

public class P406_QueueReconstructionByHeight {
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            List<int[]> source = Arrays.asList(people);
            List<int[]> result = new ArrayList<>();

            source.sort((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
            int len = source.size();
            for (int i = 0; i < len; i++) {
                int[] aArr = source.get(i);
                result.add(aArr[1], aArr);
            }
            return result.toArray(new int[0][]);
        }
    }
}
