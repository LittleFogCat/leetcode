package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P135_Candy {
    public int candy(int[] ratings) {
        if (ratings.length < 2) return ratings.length;
        int[] result = new int[ratings.length];
        if (result[0] <= result[1]) result[0] = 1;
        if (result[result.length - 1] <= result[result.length - 2]) result[result.length - 1] = 1;
        // 谷：
        for (int i = 1; i < ratings.length - 1; i++) {
            if (ratings[i] <= ratings[i - 1] && ratings[i] <= ratings[i + 1]) {
                result[i] = 1;
            }
        }
        // 从左到右向上的山腰：
        for (int i = 0; i < result.length; ) {
            if (result[i] == 1) { // 谷底
                for (i = i + 1; i < result.length && ratings[i] > ratings[i - 1]; i++) {
                    result[i] = result[i - 1] + 1;
                } // 到走下坡路为止
            } else i++; // 非谷底，说明在走下坡路
        }
        // 从左到右下降的山腰：
        for (int i = result.length - 1; i >= 0; ) {
            if (result[i] == 1) { // 谷底
                for (i = i - 1; i >= 0 && ratings[i] > ratings[i + 1]; i--) {
                    // 如果遇到山峰，那么取左右二者较大值
                    result[i] = Math.max(result[i], result[i + 1] + 1); // 走上坡路中。。。
                } // 到走下坡路为止
            } else i--; // 非谷底，说明在走下坡路
        }
//        System.out.println(Arrays.toString(result));
        int sum = 0;
        for (int i : result) sum += i;
        return sum;
    }
}
