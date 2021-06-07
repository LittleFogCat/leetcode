package top.littlefogcat.leetcode;

public class P1379_FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (cloned == null || cloned.val == target.val) return cloned;
        TreeNode found = cloned.left != null ? getTargetCopy(original, cloned.left, target) : null;
        return found != null ? found : getTargetCopy(original, cloned.right, target);
    }
}
