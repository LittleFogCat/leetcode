package top.littlefogcat.leetcode.interview;


public class P0402_MinimumHeightTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int p, int q) {
        if (p > q) return null;
        if (p == q) return new TreeNode(nums[p]);
        int mid = (p + q + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, p, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, q);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
