package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P1769_MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public int[] minOperations0(String b) {
        char[] boxes = b.toCharArray();
        Set<Integer> ind = new HashSet<>();
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == '1') ind.add(i);
        }

        int[] ans = new int[boxes.length];
        for (int i = 0; i < ans.length; i++) {
            int sum = 0;
            for (Integer integer : ind) {
                sum += Math.abs(i - integer);
            }
            ans[i] = sum;
        }
        return ans;
    }

    /**
     * ans[i] = ans[i - 1] + i左侧的1数 - i右侧的1数
     */
    public int[] minOperations(String b) {
        char[] bc = b.toCharArray();
        int len = bc.length;

        int oneCount = 0; // 小球总数
        int[] onesLeft = new int[len + 1]; // 左侧的小球数
        int[] ans = new int[len]; // 结果
        for (int i = 0; i < len; i++) {
            int c = bc[i] - '0'; // 当期盒子的小球数
            if (c == 1) {
                oneCount++; // 计算小球总数
                ans[0] += i; // 计算ans[0]
                onesLeft[i + 1] = onesLeft[i] + 1; // 计算左侧小球数
            } else {
                onesLeft[i + 1] = onesLeft[i]; // 计算左侧小球数
            }
        }
        for (int i = 1; i < len; i++) {
            int left = onesLeft[i]; // 左侧的1数（不含）
            int right = oneCount - left; // 右侧的1数（含）
            // ans[i] = ans[i - 1] + i左侧的1数 - i右侧的1数
            ans[i] = ans[i - 1] + left - right;
        }
        return ans;
    }
}
