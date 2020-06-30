package top.littlefogcat.leetcode;

public class P617_MergeTwoBinaryTrees {
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
        public TreeNode mergeTrees(TreeNode n1, TreeNode n2) {
            if (n1 == null && n2 == null) {
                return null;
            } else if (n1 == null) {
                return n2;
            } else if (n2 == null) {
                return n1;
            } else {
                TreeNode n3 = new TreeNode(n1.val + n2.val);
                n3.left = mergeTrees(n1.left, n2.left);
                n3.right = mergeTrees(n1.right, n2.right);
                return n3;
            }
        }

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
