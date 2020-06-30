package top.littlefogcat.leetcode;

public class P814_BinaryTreePruning {
    /**
     * Definition for a binary tree node.
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
        public TreeNode pruneTree(TreeNode root) {
            if (root.left != null) {
                if (isZeroTree(root.left)) {
                    root.left = null;
                } else {
                    root.left = pruneTree(root.left);
                }
            }
            if (root.right != null) {
                if (isZeroTree(root.right)) {
                    root.right = null;
                } else {
                    pruneTree(root.right);
                }
            }

            return root;
        }

        /**
         * check if a tree whether it's a zero tree
         *
         * @return true if it's a zero tree
         */
        private boolean isZeroTree(TreeNode node) {
            return node.val != 1 &&
                    (node.left == null || isZeroTree(node.left)) &&
                    (node.right == null || isZeroTree(node.right));
        }
    }

    /**
     * Postorder traversal
     */
    class Solution2 {
        public TreeNode pruneTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            root.left = pruneTree(root.left);
            root.right = pruneTree(root.right);
            if (root.val == 0 && root.left == null && root.right == null) {
                return null;
            }
            return root;
        }

    }
}
