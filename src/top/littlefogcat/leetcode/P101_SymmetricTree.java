package top.littlefogcat.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class P101_SymmetricTree {
    /**
     * 递归
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == root2) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    /**
     * 迭代
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        Queue<TreeNode> left = new ArrayDeque<>();
        Queue<TreeNode> right = new ArrayDeque<>();
        left.offer(root.left);
        right.offer(root.right);
        while (!left.isEmpty()) {
            if (right.isEmpty()) return false;
            TreeNode lNode = left.poll();
            TreeNode rNode = right.poll();
            if (lNode.val != rNode.val) return false;
            if ((lNode.left == null) != (rNode.right == null) || (lNode.right == null) != (rNode.left == null))
                return false;
            if (lNode.left != null) {
                left.offer(lNode.left);
                right.offer(rNode.right);
            }
            if (lNode.right != null) {
                left.offer(lNode.right);
                right.offer(rNode.left);
            }
        }
        return true;
    }
}
