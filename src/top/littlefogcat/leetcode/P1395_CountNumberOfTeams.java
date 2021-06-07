package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1395_CountNumberOfTeams {
    private int teams = 0;

    public int numTeams(int[] rating) {
        for (int i = 0; i < rating.length - 2; i++) {
            findLarger(rating, rating[i] + 1, i + 1, 2);
            findSmaller(rating, rating[i] - 1, i + 1, 2);
        }
        return teams;
    }

    /**
     * 找到数组中大于等于min的数
     *
     * @param min   最小需要的值
     * @param start 从数组中查找的起始位置
     * @param count 需要找的个数
     */
    private void findLarger(int[] r, int min, int start, int count) {
        for (int i = start; i < r.length; i++)
            if (r[i] >= min) if (count == 1) teams++;
            else findLarger(r, r[i] + 1, i + 1, count - 1);
    }

    private void findSmaller(int[] r, int max, int start, int count) {
        for (int i = start; i < r.length; i++)
            if (r[i] <= max) if (count == 1) teams++;
            else findSmaller(r, r[i] - 1, i + 1, count - 1);
    }

    // ----------------------------------------
    public int numTeams1(int[] rating) {
        int count = 0;
        for (int i = 0; i < rating.length - 2; i++) {
            int num1 = rating[i];
            for (int j = i + 1; j < rating.length - 1; j++) {
                int num2 = rating[j];
                if (num2 > num1) { // 在剩下的数中找到比num2大的
                    for (int k = j + 1; k < rating.length; k++) {
                        int num3 = rating[k];
                        if (num3 > num2) count++;
                    }
                } else {
                    for (int k = j + 1; k < rating.length; k++) {
                        int num3 = rating[k];
                        if (num3 < num2) count++;
                    }
                }
            }
        }
        return count;
    }

    // ----------------------------------------
    public int numTeams2(int[] rating) {
        int count = 0;
        for (int i = 1; i < rating.length - 1; i++) {
            int leftSmaller = 0, leftLarger = 0, rightSmaller = 0, rightLarger = 0;
            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i]) leftSmaller++;
                else leftLarger++;
            }
            for (int j = i + 1; j < rating.length; j++) {
                if (rating[j] < rating[i]) rightSmaller++;
                else rightLarger++;
            }
            count += leftSmaller * rightLarger;
            count += leftLarger * rightSmaller;
        }
        return count;
    }
}
