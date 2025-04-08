package top.littlefogcat.leetcode;

/**
 * 思路：二叉搜索树的中序遍历是递增数列。
 * 找到递减项则是错误项。
 */
public class P99_RecoverBinarySearchTree {
    TreeNode prev = null;
    TreeNode[] wrongNodes = new TreeNode[2];

    public void recoverTree(TreeNode root) {
        traverse(root);
        int t = wrongNodes[0].val;
        wrongNodes[0].val = wrongNodes[1].val;
        wrongNodes[1].val = t;
    }

    private void traverse(TreeNode node) {
        if (node.left != null) traverse(node.left);
        if (prev != null) {
            if (prev.val > node.val) {
                if (wrongNodes[0] == null) {
                    wrongNodes[0] = prev;
                }
                wrongNodes[1] = node;
            }
        }
        prev = node;
        if (node.right != null) traverse(node.right);
    }
}
