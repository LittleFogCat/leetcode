package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P1123_LowestCommonAncestorofDeepestLeaves {
    public static void main(String[] args) {

    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        Map<TreeNode, Integer> depthMap = new HashMap<>();

        public TreeNode lcaDeepestLeaves(TreeNode root) {
            if (root == null) return null;
            depthMap.put(null, 0);
            depth(root);
            TreeNode ancestor = root;
            while (true) {
                int leftDepth = depthMap.get(ancestor.left);
                int rightDepth = depthMap.get(ancestor.right);
                if (leftDepth == rightDepth) {
                    return ancestor;
                }
                if (leftDepth > rightDepth) {
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }
        }

        public int depth(TreeNode node) {
            if (node == null) return 0;
            if (depthMap.containsKey(node)) return depthMap.get(node);
            int depth = Math.max(depth(node.left), depth(node.right)) + 1;
            depthMap.put(node, depth);
            return depth;
        }
    }

    // common ancestor 的前提是左右子树高度相同
    class Solution1 {
        private TreeNode deepest;
        private int maxDepth = 0;

        public TreeNode lcaDeepestLeaves(TreeNode root) {
            if (root == null) return null;
            height(root, 1);
            return deepest;
        }

        public int height(TreeNode node, int depth) {
            if (node == null) return 0;
            int leftHeight = height(node.left, depth + 1);
            int rightHeight = height(node.right, depth + 1);
            if (leftHeight == rightHeight) {
                // 计算lca
                if (depth + leftHeight > maxDepth) {
                    deepest = node;
                    maxDepth = depth + leftHeight;
                }
            }
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
