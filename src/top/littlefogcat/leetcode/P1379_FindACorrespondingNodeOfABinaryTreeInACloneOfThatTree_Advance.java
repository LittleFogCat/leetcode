package top.littlefogcat.leetcode;

public class P1379_FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree_Advance {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == target || original == null) return cloned; // 遍历本节点
        TreeNode found = getTargetCopy(original.left, cloned.left, target); // 遍历左子树
        return found != null ? found : getTargetCopy(original.right, cloned.right, target); // 遍历右子树
    }
}
