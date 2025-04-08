package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

public class P2874_MaximumValueofanOrderedTripletII {

//    long[] maxSince; // 记录从该位置开始的最大值

    public long maximumTripletValue(int[] nums) {
//        maxSince = new long[nums.length];
//        maxSince[maxSince.length - 1] = nums[maxSince.length - 1];
//        for (int i = nums.length - 2; i > 0; i--) {
//            maxSince[i] = nums[i] > maxSince[i + 1] ? nums[i] : maxSince[i + 1];
//        }
        long max = 0;
        long maxDiff = 0;
        long maxUntil = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            maxUntil = nums[i - 1] > maxUntil ? nums[i - 1] : maxUntil;
            maxDiff = Math.max(maxDiff, maxUntil - nums[i]);
            long p = maxDiff * nums[i + 1];
            if (p > max) max = p;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = Util.readIntArrayFromFile("TestCase_2874");
        System.out.println(nums.length);
        P2874_MaximumValueofanOrderedTripletII solution = new P2874_MaximumValueofanOrderedTripletII();
        long time = Util.countTime(() -> {
            long ans = solution.maximumTripletValue(nums);
            System.out.println(ans);
        });
        System.out.println("Use " + time);
    }
}