package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 * 使用Map保存序列长度。
 */
public class P1218_LongestArithmeticSubsequenceOfGivenDifference {
    /**
     * 使用数组
     */
    public int longestSubsequence(int[] arr, int difference) {
        int[] map = new int[20002];
        int offset = 10000;
        for (int num : arr) {
            int numLoc = num + offset;
            int lastLoc = numLoc - difference;
            map[numLoc] = lastLoc < 0 || lastLoc > map.length - 1 ? 1 : map[lastLoc] + 1;
        }
        int longest = 0;
        for (int len : map) {
            if (len > longest) longest = len;
        }
        return longest;
    }

    /**
     * 使用HashMap
     */
    public int longestSubsequence1(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            int last = num - difference;
            map.put(num, map.getOrDefault(last, 0) + 1);
        }
        return map.values().stream().max(Comparator.comparingInt(n -> n)).get();
    }

    public static void main(String[] args) {
        int[] arr = Util.readIntArrayFromFile("TestCase_1218");
        P1218_LongestArithmeticSubsequenceOfGivenDifference p = new P1218_LongestArithmeticSubsequenceOfGivenDifference();
        long time = TimeCounter.count(() -> {
            int ans = p.longestSubsequence(arr, -1960);
            System.out.println("ans: " + ans);
        });
        System.out.println("use time: " + time);
    }
}
