package top.littlefogcat.leetcode;

public class P513_FindBottomLeftTreeValue {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        private int bottomLeftValue;
        private int maxLevel;

        public int findBottomLeftValue(TreeNode root) {
            bottomLeftValue = root.val;
            find(root, 0);
            return bottomLeftValue;
        }

        private void find(TreeNode node, int level) {
            if (node.left == null && node.right == null) {
                if (level > maxLevel) {
                    bottomLeftValue = node.val;
                    maxLevel = level;
                }
            } else if (node.right == null) {
                find(node.left, level + 1);
            } else if (node.left == null) {
                find(node.right, level + 1);
            } else {
                find(node.left, level + 1);
                find(node.right, level + 1);
            }
        }
    }
}
