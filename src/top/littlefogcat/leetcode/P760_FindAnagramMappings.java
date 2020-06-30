package top.littlefogcat.leetcode;

public class P760_FindAnagramMappings {
    class Solution {
        public int[] anagramMappings(int[] A, int[] B) {
            int[] order = new int[A.length];

            for (int a1 = 0; a1 < A.length; a1++) {
                for (int b1 = 0; b1 < B.length; b1++) {
                    if (B[b1] == A[a1]) {
                        order[a1] = b1;
                        break;
                    }
                }
            }

            return order;
        }
    }
}
