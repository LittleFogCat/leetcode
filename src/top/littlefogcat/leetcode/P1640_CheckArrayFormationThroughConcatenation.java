package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class P1640_CheckArrayFormationThroughConcatenation {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        int count = 0;
        for (int[] piece : pieces) {
            if (piece.length == 0) continue;
            int first = -1; // 在原数组中匹配piece[0]
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == piece[0]) first = i;
            }
            if (first == -1) return false;
            for (int i = 1; i < piece.length; i++) {
                if (first + i >= arr.length || arr[first + i] != piece[i]) return false;
            }
            count += piece.length;
        }
        return count == arr.length;
    }

    public boolean canFormArray1(int[] arr, int[][] pieces) {
        Map<Integer, Integer> numPosMap = new HashMap<>(); // 数字-位置映射
        for (int i = 0; i < arr.length; i++) {
            numPosMap.put(arr[i], i);
        }
        int count = 0;
        for (int[] piece : pieces) {
            if (piece.length == 0) continue;
            if (!numPosMap.containsKey(piece[0])) return false;
            int first = numPosMap.get(piece[0]); // 查找首位
            for (int i = 1; i < piece.length; i++) {
                if (first + i >= arr.length || arr[first + i] != piece[i]) return false;
            }
            count += piece.length;
        }
        return count == arr.length;
    }
}
