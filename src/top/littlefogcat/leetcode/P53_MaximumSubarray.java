package top.littlefogcat.leetcode;

public class P53_MaximumSubarray {
    class Solution {
        public int maxSubArray(int[] nums) {
            int max = nums[nums.length - 1];
            int lastMaxSum = nums[nums.length - 1];

            for (int i = nums.length - 2; i >= 0; i--) {
                int ai = nums[i];
                lastMaxSum = lastMaxSum > 0 ? ai + lastMaxSum : ai;
                max = Math.max(max, lastMaxSum);
            }
            return max;
        }
    }

    class Solution1 {
        public int maxSubArray(int[] nums) {
            int sumTmp; // = S[0, i]
            int minTmp; //记录阶段性的最小值
            int maxTmp; //记录阶段性的最大值
            int max; // 最大序列和
            boolean allNeg = true; // 特殊情况，如果所有都是负数
            minTmp = maxTmp = sumTmp = max = nums[0];

            for (int ai : nums) {
                sumTmp += ai; // = S[0, i]
                if (allNeg) {
                    if (max < ai) max = ai;
                    if (ai >= 0) allNeg = false;
                }
                if (sumTmp < minTmp) {
                    minTmp = maxTmp = sumTmp; // 新的阶段最小值
                } else if (sumTmp > maxTmp) {
                    maxTmp = sumTmp; // 新的阶段最大值
                    int diff = sumTmp - minTmp;
                    if (diff > max) {
                        max = diff; // 判断是否是最大差值
                    }
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Solution1 s1 = new P53_MaximumSubarray().new Solution1();
        int i = s1.maxSubArray(new int[]{-1, 0});
        System.out.println(i);
    }
}
