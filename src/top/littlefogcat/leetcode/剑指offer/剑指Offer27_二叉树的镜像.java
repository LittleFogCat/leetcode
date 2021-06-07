package top.littlefogcat.leetcode.剑指offer;

import top.littlefogcat.leetcode.TreeNode;

public class 剑指Offer27_二叉树的镜像 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(left);
        return root;
    }
}
