package top.littlefogcat.leetcode;

public class P657_JudgeRouteCircle {
    class Solution {

        public boolean judgeCircle(String moves) {
            int LR = 0;
            int UD = 0;
            for (char c : moves.toCharArray()) {
                switch (c) {
                    case 'R':
                        LR++;
                        break;
                    case 'L':
                        LR--;
                        break;
                    case 'U':
                        UD++;
                        break;
                    case 'D':
                        UD--;
                        break;
                }
            }
            return LR == 0 && UD == 0;
        }
    }
}
