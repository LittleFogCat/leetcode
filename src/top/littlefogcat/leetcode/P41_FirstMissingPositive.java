package top.littlefogcat.leetcode;

import java.util.Arrays;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * <p>
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 */
public class P41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        /*
         * 首先要明确的是，因为数组长度最大是500000，所以
         * 没有出现的最小正整数最大可能是500001。
         */
        int flag = -600000; // 标记
        for (int i = 0; i < nums.length; i++) {
            // 首先剔除负数，因为不影响结果，后面需要用负数做标记
            if (nums[i] < 0) nums[i] = 0;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 将nums[num-1]标记，表示该数字出现过，类似map
            // 例如原数组为{1, 2, 3, ..., n}
            // 对应数组位置{0, 1, 2, ..., n-1}
            // 1. 如果一个数字出现过，将其数组对应位置元素置为flag
            // 2. 如果对应位置元素尚未遍历到，那么就先将其置为相反数，等遍历到再置为flag
            if (num == flag) continue; // 当前位置是标记，不做任何事
            if (num < 0) { // 当前位置被标记过 *对应第2条
                num = -num;
                nums[i] = flag; // *对应第2条
            }
            if (num == 0) continue; // 非正数不影响结果
            if (num > nums.length) continue; // 超出数组长度不影响结果
            // 标记nums[num-1]为负数flag，表示num出现过
            // 如果对应位置有正数，则将其置负而不是标记为flag
            if (num - 1 <= i) { // 对应位置已经遍历过了，可以直接覆盖 *对应第1条
                nums[num - 1] = flag;
            } else { // 对应位置没有遍历过，不能直接覆盖，需要先保留信息 *对应第2条
                int old = nums[num - 1];
                if (old == 0) { // 对应位置是0，直接标记
                    nums[num - 1] = flag;
                } else if (old > 0) { // 对应位置有正数，需要先保留信息，标记且记录数字
                    nums[num - 1] = -old;
                }
                // else 之前已经标记过了，不需要做任何事
            }
        }
        System.out.println(Arrays.toString(nums));
        // 找出第一个值不为flag的元素位置，即是第一个未出现的正整数
        int firstMissing = 0;
        for (; firstMissing < nums.length; firstMissing++) {
            if (nums[firstMissing] != flag) break;
        }
        return firstMissing + 1;
    }

}
