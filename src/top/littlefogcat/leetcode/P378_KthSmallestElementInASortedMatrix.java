package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P378_KthSmallestElementInASortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int[] arr = new int[matrix.length * matrix[0].length];
        int a = 0;
        for (int[] row : matrix) {
            for (int i : row) {
                arr[a++] = i;
            }
        }
        Arrays.sort(arr);
        return arr[k - 1];
    }

//
//    /**
//     * 若使用二分查找更快
//     */
//    public int kthSmallest(int[][] matrix, int k) {
//        int[] smallest = new int[k]; // 降序保存最小的k个
//        Arrays.fill(smallest, Integer.MAX_VALUE);
//        for (int[] row : matrix) {
//            if (row[0] >= smallest[0]) break;
//            for (int num : row) {
//                if (num >= smallest[0]) break;
//                insert(smallest, num);
//            }
//        }
//
//        return smallest[0];
//    }
//
//    /**
//     * 若使用二分查找更快
//     *
//     * @param arr 降序数组
//     */
//    private void insert(int[] arr, int val) {
//        int index = binarySearch(arr, 0, arr.length - 1, val);
//        if (index == -1) return;
//        if (index == 0) {
//            arr[0] = val;
//        } else {
//            System.arraycopy(arr, 1, arr, 0, index);
//            arr[index] = val;
//        }
//    }
//
//    /**
//     * 找到最接近val的那个值
//     *
//     * @param arr 降序排列的数组
//     * @param end 包含
//     * @return 如果数组中所有元素都比val小，返回-1；否则返回k，满足arr[k] >= val >= arr[k+1]
//     */
//    private int binarySearch(int[] arr, int start, int end, int val) {
//        if (val >= arr[start]) return start - 1;
//        if (start == end) return start;
//        if (start + 1 == end) return arr[end] <= val ? start : end;
//
//        int center = (start + end) / 2;
//        if (arr[center] == val) {
//            return center;
//        } else if (arr[center] > val) { // 从右边找
//            return binarySearch(arr, center, end, val);
//        } else { // 从左边找
//            return binarySearch(arr, start, center, val);
//        }
//    }

    public static void main(String[] args) {
        P378_KthSmallestElementInASortedMatrix p378 = new P378_KthSmallestElementInASortedMatrix();
        int[][] testCase = {{4, 7, 11}, {7, 11, 16}, {10, 11, 20}};

        System.out.println(p378.kthSmallest(testCase, 6));
    }

}
