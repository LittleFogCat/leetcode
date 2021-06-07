package top.littlefogcat.leetcode;

import java.util.*;

public class P442_FindAllDuplicatesInAnArray {
    /**
     * 这个可能是最满足题目条件的解法
     * 不要额外空间，时间O(n)
     */
    class Solution {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> result = new ArrayList<>();
            int len = nums.length;
//            System.out.println(Arrays.toString(nums));
            // Cause 1 <= ai <= n,
            // if nums[i - 1] == i, we call it is at the 'right place'.
            // Each time we come to an item, we put it to the 'right place'.
            // If an int appears twice, we put it twice, and then
            // when we put it to the 'right place', there is already it
            for (int i = 0; i < len; ) {
                int aInt = nums[i]; // 1 <= aInt <= len, so we exchange aInt and nums[aInt - 1]
                if (aInt == i + 1 || aInt == 0) { // if it's the right place, continue
                    i++;
                    continue;
                }
                int anotherInt = nums[aInt - 1];
                if (aInt == anotherInt) {
                    nums[i] = 0; // we should set nums[i] to 0 to avoid unnecessary calculate
                    result.add(aInt);
                    i++;
                } else {
                    int tmp = nums[i];
                    nums[i] = nums[aInt - 1];
                    nums[aInt - 1] = tmp;
//                    System.out.println(Arrays.toString(nums));
                }
                // before i++, a[i] must equals 0 or i + 1
            }
            return result;
        }
    }

    /**
     * 最容易想到
     */
    class Solution2 {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> result = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            for (int aInt : nums) {
                if (!set.add(aInt)) {
                    result.add(aInt);
                }
            }
            return result;
        }
    }

    /**
     * 最快，但是需要额外空间
     */
    class Solution3 {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> result = new ArrayList<>();
            int[] map = new int[nums.length + 1];
            for (int aInt : nums) {
                if (map[aInt] == 1) {
                    result.add(aInt);
                } else {
                    map[aInt] = 1;
                }
            }
            return result;
        }
    }
}
