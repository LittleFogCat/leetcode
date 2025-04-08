package top.littlefogcat.leetcode.extra;

import top.littlefogcat.leetcode.Test;

/**
 * LeetCode - 548. Split array with equal sum
 * 将一个数组分割成两个相等长度的数组，且二者之和尽量接近。返回较小的那个和。
 * <p>
 * 例：
 * [1, 2, 4, 6]
 * 分割为：
 * [1, 6] [2, 4]
 * 返回6
 */
public class E04_SplitArrayWithClosestSum {

    public int splitArrayWithClosestSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 已选择个数最大为 nums.length / 2 - 1，故长度为 nums.length / 2
        // 剩余和最大为 sum/2，故长度为 sum / 2 + 1
        Integer[][] dp = new Integer[nums.length / 2][sum / 2 + 1];
        return choose0(nums, 0, sum / 2, dp);
    }

    /**
     * 选择之后把选择的数放在数组最后。
     * <p>
     * chosen: 已经选择的个数
     * sum: 需要凑的和
     * 返回: 当前情况下能达到的最接近sum的和
     * <p>
     * 存在的问题：大量重复计算，需要进行记忆化
     */
    private int choose(int[] nums, int chosen, int sum) {
        if (chosen == nums.length / 2) return 0;
        int max = 0;
        for (int i = 0; i < nums.length - chosen; i++) {
            if (nums[i] > sum) continue;
            // 选择其中一个数
            int current = nums[i];
            swap(nums, i, nums.length - chosen - 1); // 把nums[i]放到数组最后，表示被选择
            int currentSum = current + choose(nums, chosen + 1, sum - current);
            if (currentSum > max) max = currentSum;
            swap(nums, i, nums.length - chosen - 1); // 交换回来，进行下一次迭代
        }
        return max;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 其他同choose()函数，为了避免重复计算，进行记忆化。
     * 其中记忆的两个维度为：dp[chosen][sum]
     */
    private int choose0(int[] nums, int chosen, int sum, Integer[][] dp) {
        if (chosen == nums.length / 2) return 0;
        if (dp[chosen][sum] == null) {
            int max = 0;
            for (int i = 0; i < nums.length - chosen; i++) {
                if (nums[i] > sum) continue;
                // 选择其中一个数
                int current = nums[i];
                swap(nums, i, nums.length - chosen - 1); // 把nums[i]放到数组最后，表示被选择
                int currentSum = current + choose0(nums, chosen + 1, sum - current, dp);
                if (currentSum > max) max = currentSum;
                swap(nums, i, nums.length - chosen - 1); // 交换回来，进行下一次迭代
            }
            dp[chosen][sum] = max;
        }
        return dp[chosen][sum];
    }

    public static void main(String[] args) {
        E04_SplitArrayWithClosestSum e = new E04_SplitArrayWithClosestSum();
        Test test = new Test() {
            @Override
            public Object onTest(Object[] params) {
                int[] nums = (int[]) params[0];
                return e.splitArrayWithClosestSum(nums);
            }
        };
        test.addTestCase(197, (Object) new int[]{1, 7, 90, 98, 99, 100});
        test.addTestCase(13, (Object) new int[]{1, 2, 4, 5, 6, 8});
        test.addTestCase(555, (Object) new int[]{111, 222, 333, 888});
        test.addTestCase(13, (Object) new int[]{1, 7, 8, 12});
        test.run();
    }
}
