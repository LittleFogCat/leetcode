package top.littlefogcat.leetcode;

public class P861_ScoreAfterFlippingMatrix {
    public static void main(String[] args) {
        Solution solution = new P861_ScoreAfterFlippingMatrix().new Solution();
        int[] a = {1, 0, 0, 1};
        System.out.println(solution.cal(a));
    }

    /**
     * 要求最大，只要最高位全是1就行了。后面的列1尽量多即可。
     */
    class Solution {
        public int matrixScore(int[][] A) {
            // make all row first num 1
            for (int i = 0; i < A.length; i++) {
                if (A[i][0] == 0) {
                    reverseRow(A, i);
                }
            }
            for (int i = 1; i < A[0].length; i++) {
                // i for column
                // check how much 1s in this column
                int ones = 0;
                for (int[] aA : A) {
                    if (aA[i] == 1) {
                        ones++;
                    }
                }
                if (ones < (1 + A.length) / 2) {
                    reverseColumn(A, i);
                }
            }
            int result = 0;
            for (int[] a : A) {
                result += cal(a);
            }
            return result;
        }

        private int cal(int[] A) {
            int sum = 0, pow = 1;
            int len = A.length;
            for (int i = len - 1; i >= 0; i--) {
                sum += pow * A[i];
                pow *= 2;
            }
            return sum;
        }

        private void reverseColumn(int[][] A, int column) {
            for (int i = 0; i < A.length; i++) {
                A[i][column] = 1 - A[i][column];
            }
        }

        private void reverseRow(int[][] A, int row) {
            for (int i = 0; i < A[0].length; i++) {
                A[row][i] = 1 - A[row][i];
            }
        }
    }

    /**
     * 这个解法是看了discuss之后领悟的，思路类似，运行时长也没提升，但是非常简洁
     */
    class Solution2 {
        public int matrixScore(int[][] A) {
            int columns = A[0].length;
            int rows = A.length;
            int totalSum = pow2(columns - 1) * rows;
            for (int i = 1; i < columns; i++) {
                int cSum = 0;
                for (int[] aRow : A) cSum += aRow[i] == aRow[0] ? 1 : 0;
                totalSum += Math.max(cSum, rows - cSum) * pow2(columns - i - 1);
            }
            return totalSum;
        }

        private int pow2(int b) {
            return 1 << b;
        }
    }
}
