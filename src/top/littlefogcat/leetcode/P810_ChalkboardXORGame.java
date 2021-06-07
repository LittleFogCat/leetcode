package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P810_ChalkboardXORGame {
    public boolean xorGame0(int[] nums) {
        int origXor = 0;
        for (int num : nums) origXor ^= num;
        if (origXor == 0) return true;
        Map<Integer, Integer> numsCount = new HashMap<>();
        for (int num : nums) numsCount.put(num, numsCount.getOrDefault(num, 0) + 1);
        boolean aliceWin = true;
        for (Integer count : numsCount.values()) if (1 == (count & 1)) aliceWin = !aliceWin;
        return aliceWin;
    }

    public boolean xorGame(int[] nums) {
        int arrXor = 0;
        for (int num : nums) arrXor ^= num;
        return arrXor == 0 || nums.length % 2 == 0;
    }
}
