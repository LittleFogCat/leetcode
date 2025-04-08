package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

public class P1690_StoneGameVII {
    //    public int stoneGameVII(int[] stones) {
//        int[] points = new int[2]; // 0 Alice 1 Bob
//        int sum = 0;
//        for (int st : stones) sum += st;
//        System.out.println("初始：" + Arrays.toString(stones));
//        stoneGameVII(stones, 0, stones.length - 1, points, 0, sum);
//        return points[0] - points[1];
//    }
//
//    /**
//     * 需要接下来的子数组左右端两个数之中较小值尽可能大；
//     */
////    public void stoneGameVII(int[] stones, int left, int right, int[] points, int round, int sum) {
////        int removeLeft = Math.min(stones[left], stones[right - 1]); // 移除左边之后两头较小值
////        int removeRight = Math.min(stones[left + 1], stones[right]); // 移除右边之后两头较小值
////        if (right - left == 1) { // 只剩2枚，选择小的
////            if (stones[left] < stones[right]) { // 移除左边
////                points[round] += stones[right];
////                print(round, stones[left], stones[right], points, stones, left + 1, right);
////            } else {
////                points[round] += stones[left];
////                print(round, stones[right], stones[left], points, stones, left, right - 1);
////            }
////        } else if (removeLeft > removeRight) {
////            // 左边大，移除右边
////            points[round] += sum - stones[right];
////            print(round, stones[right], sum - stones[right], points, stones, left, right - 1);
////            stoneGameVII(stones, left, right - 1, points, 1 - round, sum - stones[right]);
////        } else if (removeLeft < removeRight) {
////            // 右边大，移除左边
////            points[round] += sum - stones[left];
////            print(round, stones[left], sum - stones[left], points, stones, left + 1, right);
////            stoneGameVII(stones, left + 1, right, points, 1 - round, sum - stones[left]);
////        } else { // 如果二者相同，那么尽可能让自己获得更多的分数，则移除左右两端中较小的数
////            if (stones[left] > stones[right]) { // 移除右边
////                points[round] += sum - stones[right];
////                print(round, stones[right], sum - stones[right], points, stones, left, right - 1);
////                stoneGameVII(stones, left, right - 1, points, 1 - round, sum - stones[right]);
////            } else { // 移除左边
////                points[round] += sum - stones[left];
////                print(round, stones[left], sum - stones[left], points, stones, left + 1, right);
////                stoneGameVII(stones, left + 1, right, points, 1 - round, sum - stones[left]);
////            }
////        }
////    }
//    public void stoneGameVII(int[] stones, int left, int right, int[] points, int round, int sum) {
//        if (right - left == 1) { // 只剩2枚，选择小的
//            if (stones[left] < stones[right]) { // 移除左边
//                points[round] += stones[right];
//                print(round, stones[left], stones[right], points, stones, left + 1, right);
//            } else {
//                points[round] += stones[left];
//                print(round, stones[right], stones[left], points, stones, left, right - 1);
//            }
//            return;
//        }
//        // 移除左边
//        int me1 = stones[left];
//        int he1 = Math.max(stones[left + 1], stones[right]);
//        int diff1 = me1 - he1;
//        // 移除右边
//        int me2 = stones[right];
//        int he2 = Math.max(stones[left], stones[right - 1]);
//        int diff2 = me2 - he2;
//
//        if (diff1 > diff2) {
//            // 左边大，移除右边
//            points[round] += sum - stones[right];
//            print(round, stones[right], sum - stones[right], points, stones, left, right - 1);
//            stoneGameVII(stones, left, right - 1, points, 1 - round, sum - stones[right]);
//        } else if (diff1 < diff2) {
//            // 右边大，移除左边
//            points[round] += sum - stones[left];
//            print(round, stones[left], sum - stones[left], points, stones, left + 1, right);
//            stoneGameVII(stones, left + 1, right, points, 1 - round, sum - stones[left]);
//        }
//        if (diff1 == diff2) {
//            if (stones[left] > stones[right]) { // 移除右边
//                points[round] += sum - stones[right];
//                print(round, stones[right], sum - stones[right], points, stones, left, right - 1);
//                stoneGameVII(stones, left, right - 1, points, 1 - round, sum - stones[right]);
//            } else { // 移除左边
//                points[round] += sum - stones[left];
//                print(round, stones[left], sum - stones[left], points, stones, left + 1, right);
//                stoneGameVII(stones, left + 1, right, points, 1 - round, sum - stones[left]);
//            }
//        }
//    }
//
//    private void print(int round, int remove, int gain, int[] points, int[] stones, int left, int right) {
//        String name = round == 0 ? "Alice" : "Bob";
//        int[] arr = new int[right - left + 1];
//        System.arraycopy(stones, left, arr, 0, arr.length);
//        System.out.println(String.format("%s移除 %d ，得分 %d 。游戏情况：爱丽丝 = %d ，鲍勃 = %d ，石子 = %s 。",
//                name, remove, gain, points[0], points[1], Arrays.toString(arr)));
//    }

    // ------------------ DP递归 -------------------

    public int stoneGameVII1(int[] stones) {
        int[][] dp = new int[stones.length][stones.length];
        int sum = 0;
        for (int st : stones) sum += st;
        return diff1(stones, 0, stones.length - 1, sum, dp);
    }

    private int diff1(int[] stones, int left, int right, int sum, int[][] dp) {
        if (right == left) return 0;
        if (right - left == 1) {
            dp[left][right] = Math.max(stones[left], stones[right]);
        } else {
            // 移除左边
            int gainLeft = sum - stones[left];
            int diffRemoveLeft = gainLeft - diff1(stones, left + 1, right, gainLeft, dp);
            // 移除右边
            int gainRight = sum - stones[right];
            int diffRemoveRight = gainRight - diff1(stones, left, right - 1, gainRight, dp);

            dp[left][right] = Math.max(diffRemoveLeft, diffRemoveRight);
        }
        return dp[left][right];
    }

    // ------------------ DP递推 -------------------

    int count1 = 0;

    public int stoneGameVII2(int[] stones) {
        int[] dp = new int[stones.length];
        int[] sums = new int[stones.length];
        System.arraycopy(stones, 0, sums, 0, stones.length);
        recur(stones, 0, 1, dp, sums);
        return dp[0];
    }

    // 递推
    private void recur(int[] stones, int x, int y, int[] dp, int[] sums) {
        count1++;
        sums[x] = sums[x] + stones[y];
        int gx = sums[x] - stones[x]; // 移除左边
        int gy = sums[x] - stones[y]; // 移除右边
        dp[x] = Math.max(gx - dp[x + 1], gy - dp[x]); // 动态规划递推式
        if (x == 0 && y == stones.length - 1) return; // 完毕了！
        // 继续递推
        if (y == stones.length - 1) recur(stones, 0, y - x + 1, dp, sums); // 递推下一轮
        else recur(stones, x + 1, y + 1, dp, sums); // 递推dp[x+1][y+1]
    }

    // ------------------ DP递推：循环 -------------------

    int count2 = 0;

    public int stoneGameVII(int[] stones) {
        int[] dp = new int[stones.length];
        int[] sums = new int[stones.length];
        System.arraycopy(stones, 0, sums, 0, stones.length);
        for (int max = stones.length - 1; max > 0; max--) {
            for (int x = 0; x < max; x++) {
                count2++;
                int y = x + stones.length - max;
                sums[x] = sums[x] + stones[y];
                int gx = sums[x] - stones[x]; // 移除左边
                int gy = sums[x] - stones[y]; // 移除右边
                dp[x] = Math.max(gx - dp[x + 1], gy - dp[x]); // 动态规划递推式
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        P1690_StoneGameVII p = new P1690_StoneGameVII();
        int[] arr = Util.arr(121, 903, 609, 929, 646, 419, 823, 722, 223, 170, 8, 704, 102, 803, 639, 548, 364, 306, 440, 65, 933, 123, 21, 376, 215, 798, 280, 232, 942, 513, 463, 567, 34, 642, 958, 823, 37, 722, 223, 170, 8, 704, 102, 803, 639, 548, 364, 306, 440, 65, 933, 123, 21, 376, 215, 798, 280, 232, 942, 513, 463, 567, 34, 642, 958, 823, 37, 605, 784);
        p.stoneGameVII(arr);
        p.stoneGameVII2(arr);
        System.out.println(p.count1);
        System.out.println(p.count2);
    }
}
