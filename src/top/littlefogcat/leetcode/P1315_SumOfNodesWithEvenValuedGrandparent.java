package top.littlefogcat.leetcode;

public class P1315_SumOfNodesWithEvenValuedGrandparent {
    private int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        traverse(root);
        return sum;
    }

    private void traverse(TreeNode node) {
        if (node.val % 2 == 0) sum += grandSonSum(node);
        if (node.left != null) traverse(node.left);
        if (node.right != null) traverse(node.right);
    }

    private int grandSonSum(TreeNode node) { // 孙子节点值的和
        int sum = 0;
        if (node.left != null) {
            if (node.left.left != null) sum += node.left.left.val;
            if (node.left.right != null) sum += node.left.right.val;
        }
        if (node.right != null) {
            if (node.right.left != null) sum += node.right.left.val;
            if (node.right.right != null) sum += node.right.right.val;
        }
        return sum;
    }
}
