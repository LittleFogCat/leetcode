package top.littlefogcat.leetcode;

/**
 * 思路：DFS
 */
public class P129_SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, Integer base) {
        int value = base * 10 + root.val;
        if (root.left == null && root.right == null) {
            return value;
        } else if (root.left == null) {
            return dfs(root.right, value);
        } else if (root.right == null) {
            return dfs(root.left, value);
        } else {
            return dfs(root.left, value) + dfs(root.right, value);
        }
    }
}
