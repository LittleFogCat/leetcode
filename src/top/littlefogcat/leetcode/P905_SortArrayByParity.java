package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P905_SortArrayByParity {

    public static void main(String[] args) {
        Solution solution = new P905_SortArrayByParity().new Solution();
        int[] A = {1, 2, 3, 4};
        System.out.println(Arrays.toString(solution.sortArrayByParity(A)));
    }

    class Solution {
        public int[] sortArrayByParity(int[] A) {
            for (int i = 0, j = A.length - 1; i < j; ) {
                if (A[i] % 2 == 0 && A[j] % 2 == 1) {
                    i++;
                    j--;
                } else if (A[i] % 2 == 0) {
                    i++;
                } else if (A[j] % 2 == 1) {
                    j--;
                } else {
                    int t = A[i];
                    A[i] = A[j];
                    A[j] = t;
                }
            }
            return A;
        }
    }
}
