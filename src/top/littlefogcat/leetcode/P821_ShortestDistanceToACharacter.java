package top.littlefogcat.leetcode;

public class P821_ShortestDistanceToACharacter {
    class Solution {
        public int[] shortestToChar(String S, char C) {
            int len = S.length();
            int[] distance = new int[len];
            int lastCIndex = -1;
            int firstCIndex = -1;
            
            // left to right
            for (int i = 0; i < len; i++) {
                if (S.charAt(i) == C) {
                    if (firstCIndex == -1) {
                        firstCIndex = i;
                        for (int j = i - 1; j >= 0; j--) {
                            distance[j] = i - j;
                        }
                    }
                    distance[i] = 0;
                    lastCIndex = i;
                } else {
                    distance[i] = i - lastCIndex;
                }
            }

            // right to left
            // for chars between firstC and lastC
            for (int i = lastCIndex - 1; i > firstCIndex; i--) {
                if (S.charAt(i) == C) {
                    lastCIndex = i;
                    continue;
                }
                distance[i] = distance[i] < lastCIndex - i ? distance[i] : lastCIndex - i;
            }

            return distance;
        }
    }
}
