package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.HashSet;
import java.util.Set;

public class P416_PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            if (num > max) max = num;
        }
        if (sum % 2 == 1) return false;
        int k = sum / 2; // k 为目标和
        if (max > k) return false;
        if (max == k) return true;

        // rec 记录该数组所能构建的所有和值
        int[] rec = new int[k + 1];
        // 每次遍历一个数，将该数记录，并将该数和之前所有记录过的和相加的和记录
        for (int num : nums) {
            // 隐含条件：num < k = rec.length - 1
            for (int prevSum = rec.length - 1; prevSum >= 0; prevSum--) { // 必须倒序，否则会重复计算
                int newSum = num + prevSum; // 将 nums[i] 与之前的记录求和
                if (rec[prevSum] == 1 && newSum < rec.length) {
                    if (newSum == k) return true; // 找到了！
                    rec[newSum] = 1;
                }
            }
            rec[num] = 1;
        }
        // 没找到！
        return false;
    }

    public static void main(String[] args) {
        var nums = Util.arr(2, 2, 3, 5);
        P416_PartitionEqualSubsetSum solution = new P416_PartitionEqualSubsetSum();
        boolean b = solution.canPartition(nums);
        System.out.println(b);
    }
}
