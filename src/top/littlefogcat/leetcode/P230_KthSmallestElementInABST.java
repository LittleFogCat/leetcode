package top.littlefogcat.leetcode;

public class P230_KthSmallestElementInABST {
    private int val;
    private int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return val;
    }

    private void traverse(TreeNode node, int k) {
        if (node.left != null) traverse(node.left, k);
        count++;
        if (count == k) {
            val = node.val;
            return;
        }
        if (node.right != null) traverse(node.right, k);
    }
}
