package top.littlefogcat.leetcode;

public class P289_GameofLife {

    /**
     * Any live cell with fewer than two live neighbors dies as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population.
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     */
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] |= nextState(board, i, j) << 1;  // 在左移 1 位的地方记录新值
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] >> 1; // 新值覆盖旧值
            }
        }
    }


    private int nextState(int[][] board, int i, int j) {
        int neighbors = livingNeighbors(board, i, j);
        if (board[i][j] == 1) {
            // Any live cell with fewer than two live neighbors dies as if caused by under-population.
            if (neighbors < 2) return 0;
            // Any live cell with two or three live neighbors lives on to the next generation.
            if (neighbors == 2 || neighbors == 3) return 1;
            // Any live cell with more than three live neighbors dies, as if by over-population.
            return 0;
        } else {
            // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
            return neighbors == 3 ? 1 : 0;
        }
    }

    // 存活的邻居
    private int livingNeighbors(int[][] board, int i, int j) {
        int neighbors = 0;
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int j1 = j - 1; j1 <= j + 1; j1++) {
                if (i1 < 0 || i1 >= board.length || j1 < 0 || j1 >= board[0].length || i1 == i && j1 == j) continue;
                neighbors += board[i1][j1] & 0x1; // 使用位运算取旧值
            }
        }
        return neighbors;
    }

}
