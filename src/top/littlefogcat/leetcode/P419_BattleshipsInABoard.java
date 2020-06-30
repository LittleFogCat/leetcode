package top.littlefogcat.leetcode;

public class P419_BattleshipsInABoard {

    class Solution {

        public int countBattleships(char[][] board) {
            int shipNum = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (isEndOfShip(board, i, j)) {
                        shipNum++;
                    }
                }
            }
            return shipNum;
        }

        /**
         * 核心方法，判断这个点是否是一条船的末尾。
         * 末尾指的是水平船的最右点，或者竖直船的最下点，或者单点船本身。
         */
        private boolean isEndOfShip(char[][] board, int i, int j) {
            return board[i][j] == 'X' &&
                    (i == board.length - 1 || board[i + 1][j] != 'X') &&
                    (j == board[i].length - 1 || board[i][j + 1] != 'X');
        }
    }
}
