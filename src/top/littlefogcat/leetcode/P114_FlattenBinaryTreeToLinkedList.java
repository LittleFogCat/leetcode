package top.littlefogcat.leetcode;

public class P114_FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        myFlatten(root);
    }

    /**
     * 将树展开为链表并返回最后一个节点
     */
    private TreeNode myFlatten(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;
        // 先序遍历为：根 -> 左子树 -> 右子树
        // 则展开之后的链表为：根 -> 左子树展开 -> 右子树展开
        if (root.left == null) {
            // 如果左子树为null，则只需要展开右子树
            return myFlatten(root.right);
        }
        TreeNode tail = myFlatten(root.left); // 左子树展开
        tail.right = root.right; // 根 -> 左子树展开 -> 右子树
        root.right = root.left; // 左子树移到右侧
        root.left = null; // 左子树移到右侧
        return myFlatten(root.right); // 根 -> 左子树展开 -> 右子树展开
    }
}
