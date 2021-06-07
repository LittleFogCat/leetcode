package top.littlefogcat.leetcode.剑指offer;

import top.littlefogcat.leetcode.TreeNode;

public class 剑指Offer55I_二叉树的深度 {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
