package top.littlefogcat.leetcode;

public class P654_MaximumBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums, 0, nums.length - 1);
        }

        public TreeNode build(int[] nums, int p, int r) {
            if (p > r) {
                return null;
            }
            int maxIndex = p;
            for (int i = p; i <= r; i++) {
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            TreeNode n = new TreeNode(nums[maxIndex]);
            n.left = build(nums, p, maxIndex - 1);
            n.right = build(nums, maxIndex + 1, r);
            return n;
        }
    }

}
