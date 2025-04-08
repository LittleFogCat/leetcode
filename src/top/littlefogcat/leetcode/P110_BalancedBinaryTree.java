package top.littlefogcat.leetcode;

public class P110_BalancedBinaryTree {
    /**
     * 思路：
     * 在可以修改节点数字的情况下，用节点保存当前树高度。
     * 如果不可以修改，则使用Map。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (Math.abs(left - right) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        if (root.val > 5000) return root.val - 5001;
        int height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        root.val = height + 5001;
        return height;
    }

    /**
     * 思路：如果是平衡二叉树，返回树的高度，否则返回-1
     */
    public boolean isBalanced1(TreeNode root) {
        return getHeight1(root) != -1;
    }

    public int getHeight1(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight1(root.left);
        int right = getHeight1(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
