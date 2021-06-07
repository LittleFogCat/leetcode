package top.littlefogcat.leetcode;

public class P1302_DeepestLeavesSum {

    private int deepest = 0;
    private int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        helper(root, 0);
        return sum;
    }

    private void helper(TreeNode node, int depth) {
        if (depth == deepest) {
            sum += node.val;
        }
        if (depth > deepest) {
            sum = node.val;
            deepest = depth;
        }
        if (node.left != null) helper(node.left, depth + 1);
        if (node.right != null) helper(node.right, depth + 1);
    }
}
