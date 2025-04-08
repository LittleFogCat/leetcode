package top.littlefogcat.leetcode;

public class P496_NextGreaterElementI {

    /**
     * two `for` loops
     */
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            for (int i = 0; i < nums1.length; i++) {
                int aInt = nums1[i];
                int nextGreater = -2;
                for (int anotherInt : nums2) {
                    if (anotherInt == aInt) {
                        nextGreater = -1;
                    }
                    if (nextGreater == -1 && anotherInt > aInt) {
                        nextGreater = anotherInt;
                    }
                }
                nums1[i] = nextGreater;
            }
            return nums1;
        }
    }

    /**
     * use map
     */
    class Solution2 {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] map = new int[10001];
            for (int i = 0; i < nums2.length; i++) {
                map[nums2[i]] = i;
            }
            for (int i = 0; i < nums1.length; i++) {
                int index = map[nums1[i]];
                int greater = -1;
                for (int j = index + 1; j < nums2.length; j++) {
                    if (nums2[j] > nums1[i]) {
                        greater = nums2[j];
                        break;
                    }
                }
                nums1[i] = greater;
            }
            return nums1;
        }
    }
}
