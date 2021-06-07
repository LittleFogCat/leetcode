package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P32_LongestValidParentheses {
    /**
     * 求count[]数组中最宽的“山峰”的宽度。
     */
    public int longestValidParentheses(String s) {
        if (s.length() == 0) return 0;
        int[] count = new int[s.length() + 1];
        char[] str = s.toCharArray();
        count[0] = 0;
        for (int i = 0; i < str.length; i++) {
            count[i + 1] = count[i] + (str[i] == '(' ? 1 : -1);
        }
//        System.out.println(Arrays.toString(count));
        int max = 0;
        int[] leftMin = new int[count.length]; // 到i为止最低的山谷
        int[] rightMin = new int[count.length];
        for (int i = 1; i < count.length; i++) {
            leftMin[i] = count[i] < count[leftMin[i - 1]] ? i : leftMin[i - 1];
        }
        rightMin[rightMin.length - 1] = count.length - 1;
        for (int i = rightMin.length - 2; i >= 0; i--) {
            rightMin[i] = count[i] < count[rightMin[i + 1]] ? i : rightMin[i + 1];
        }
//        System.out.println(Arrays.toString(leftMin) + ", " + Arrays.toString(rightMin));
        for (int i = 0; i < count.length; i++) {
            if (!isPeak(count, i)) continue;
            int[] border = getBorder(count, i, leftMin, rightMin); // 山峰的边界（左、右）
            System.out.println("i: " + i + ", border: " + Arrays.toString(border));
            int mountainWidth = border[1] - border[0];
            if (mountainWidth > max) max = mountainWidth;
            i = border[1];
        }
        return max;
    }

    private int[] getBorder(int[] count, int i, int[] leftMin, int[] rightMin) {
        int left = count[leftMin[i]];
        int right = count[rightMin[i]];
        if (left == right) {
//            System.out.println("border: equal");
            return new int[]{leftMin[i], rightMin[i]};
        }
        int[] border = new int[2];
        if (left > right) { // 左边山谷更高
//            System.out.println("border: left");
            border[0] = leftMin[i];
            int threshold = left - 1;
            for (int j = i + 1; j < count.length; j++) {
                if (count[j] == threshold) {
                    border[1] = j - 1;
                    break;
                }
            }
        } else { // 右边山谷更高
//            System.out.println("border: right");
            border[1] = rightMin[i];
            int threshold = right - 1;
            for (int j = i; j >= 0; j--) {
                if (count[j] == threshold) {
                    border[0] = j + 1;
                    break;
                }
            }
        }
        return border;
    }

    public boolean isPeak(int[] count, int i) {
        return i != 0 && i != count.length - 1 &&
                count[i] > count[i - 1] && count[i] > count[i + 1];
    }

}
