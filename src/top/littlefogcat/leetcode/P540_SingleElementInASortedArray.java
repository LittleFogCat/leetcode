package top.littlefogcat.leetcode;

public class P540_SingleElementInASortedArray {
    class Solution {
        public int singleNonDuplicate(int[] nums) {
            for (int i = 0; i < nums.length - 1; i += 2) {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
            return nums[nums.length - 1];
        }
    }

    class Solution2 {
        public int singleNonDuplicate(int[] nums) {
            return bs(nums, 0, nums.length - 1);
        }

        public int bs(int[] nums, int p, int r) {
            // 对于一个偶数项数来说，如果不存在SingleElement，那么它必然和其后一项是相等的。
            // For an even index element, if SingleElement does not exist, it must be the same with the next element.

            // 故而，如果它和其后一项不等，正好说明SingleElement在第0项和该项之间。（或者它就是SingleElement）
            // Thus, if it's not the same with the next one, then the SingleElement is between nums[0] and it.
            // Or, it is the SingleElement.
            // 反之，SingleElement在该项和最后一项之间。
            // Otherwise, the SingleElement is between it and the last element in the array.
            if (p == r) return nums[p];
            int q = (r + p) / 2;
            if (q % 2 == 0 && nums[q + 1] != nums[q] ||
                    q % 2 == 0 && nums[q - 1] != nums[q]) {
                // 在这种情况下，单独出现的那个数应该在数组的p项和q-1项之间，
                // in this situation, the single one is between p and q-1,
                // 否则在q+1项和r项之间
                // otherwise q+1 and r
                return bs(nums, p, q - 1);
            } else {
                return bs(nums, q + 1, r);
            }
        }
    }
}
