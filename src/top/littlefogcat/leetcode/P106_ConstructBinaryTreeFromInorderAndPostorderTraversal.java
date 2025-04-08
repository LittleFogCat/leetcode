package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：同105
 */
public class P106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    /**
     * 中序遍历：[[left], node, [right]]
     * 后序遍历：[[left], [right], node]
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<>(); // inorder数组中数的序号
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(inorder, postorder, 0, postorder.length - 1,
                0, inorder.length - 1, indexMap);
    }

    // ps=postStart, pe=postEnd, is=inStart, ie=inEnd
    private TreeNode build(int[] inorder, int[] postorder,
                           int ps, int pe, int is, int ie,
                           Map<Integer, Integer> indexMap) {
        int nodeVal = postorder[pe];
        TreeNode node = new TreeNode(nodeVal);
        int idx = indexMap.get(nodeVal);
        int leftLen = idx - is;
        int rightLen = ie - idx;
        if (leftLen != 0) node.left = build(inorder, postorder, ps, ps + leftLen - 1, is, idx - 1, indexMap);
        if (rightLen != 0) node.right = build(inorder, postorder, ps + leftLen, pe - 1, idx + 1, ie, indexMap);
        return node;
    }
}
