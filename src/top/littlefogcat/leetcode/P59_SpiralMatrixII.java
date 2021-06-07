package top.littlefogcat.leetcode;

public class P59_SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] r = new int[n][n];
        int last = n * n;
        Position position = new Position(n, 0, 0);
        for (int i = 1; i <= last; i++) {
            r[position.row][position.col] = i;
            position.moveToNext();
        }
        return r;
    }

    static class Position {
        private Direction direction = Direction.right;

        private enum Direction {
            right, down, left, up
        }

        public Position(int n, int row, int col) {
            this.n = n;
            this.row = row;
            this.col = col;
        }

        int n;
        int row;
        int col;

        private void moveToNext() {
            switch (direction) {
                case up -> {
                    row--;
                    if (row - col == 1) direction = Direction.right;
                }
                case down -> {
                    row++;
                    if (row == col) direction = Direction.left;
                }
                case left -> {
                    col--;
                    if (row + col == n - 1) direction = Direction.up;
                }
                case right -> {
                    col++;
                    if (row + col == n - 1) direction = Direction.down;
                }
            }

        }
    }
}
