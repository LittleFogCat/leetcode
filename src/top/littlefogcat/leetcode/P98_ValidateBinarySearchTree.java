package top.littlefogcat.leetcode;

/**
 * 思路：采用区间限制节点范围。
 */
public class P98_ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) return true;
        // 防止溢出
        if (root.val == Integer.MIN_VALUE && root.left != null || root.val == Integer.MAX_VALUE && root.right != null)
            return false;
        // 递归，判断是否值落在区间内，并更新区间判断左子树和右子树
        return root.val >= min && root.val <= max
                && isValidBST(root.left, min, root.val - 1)
                && isValidBST(root.right, root.val + 1, max);
    }
}
