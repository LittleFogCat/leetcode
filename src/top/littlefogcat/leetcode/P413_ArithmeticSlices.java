package top.littlefogcat.leetcode;

public class P413_ArithmeticSlices {
    /**
     * 暴力穷举 O(n^3)
     */
    class Solution1 {
        public int numberOfArithmeticSlices(int[] A) {
            int len = A.length;
            if (len < 3) {
                return 0;
            }
            int count = 0;

            for (int i = 0; i < len - 2; i++) {
                for (int j = i + 2; j < len; j++) {
                    int[] sub = new int[j - i + 1];
                    System.arraycopy(A, i, sub, 0, j - i + 1);
                    if (isArithmeticSeries(sub)) {
                        count++;
                    }
                }
            }
            return count;
        }

        private boolean isArithmeticSeries(int[] arr) {
            int tolerance = arr[1] - arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] - arr[i - 1] != tolerance) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 如果一个序列是等差数列，那么他的所有长度大于等于3的子序列都是等差数列
     * 他和她的子序列一共是(n-2)(n-1)/2个
     * 那么只用找到最长的那些等差数列就行了
     * O(n)
     */
    class Solution2 {
        public int numberOfArithmeticSlices(int[] A) {
            int len = A.length;
            int count = 0;
            int startIndex = -1;// 当前等差数列的起始坐标，如果当前不是等差数列，则=-1
            for (int i = 2; i < len; i++) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    if (startIndex == -1) {
                        startIndex = i - 2;
                    }
                    if (i == len - 1) {
                        int n = i - startIndex + 1;
                        count += (n - 2) * (n - 1) / 2;
                        return count;
                    }
                } else {
                    if (startIndex != -1) {
                        int n = i - startIndex;
                        count += (n - 2) * (n - 1) / 2;
                        startIndex = -1;
                    }
                }
            }
            return count;
        }
    }
}
