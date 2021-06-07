package top.littlefogcat.leetcode;

public class P852_PeakIndexInAMountainArray {
    public static void main(String[] args) {
        Solution solution = new P852_PeakIndexInAMountainArray().new Solution();
        int[] testCase = {40, 48, 61, 75, 100, 99, 98, 39, 30, 10};
        System.out.println(solution.peakIndexInMountainArray(testCase));
    }

    /**
     * 用二分法
     */
    class Solution {
        public int peakIndexInMountainArray(int[] A) {
            int min = 0, max = A.length - 1;
            int i = A.length / 2;
            while (true) {
                if (A[i] > A[i - 1]) {
                    if (A[i] > A[i + 1]) return i;
                    else {
                        min = i;
                        i = (i + max) / 2;
                    }
                } else {
                    max = i;
                    i = (i + min) / 2;
                }
            }
        }
    }
}
