package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /**
     * 思路：
     * 已知对于二叉搜索树而言，其中序遍历的结果是一个正序数列；
     * 那么，不妨用前序遍历的`序号`替代对应的数字，建立一颗二叉搜索树，
     * 再将序号替换回对应数字即可。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indices = new HashMap<>(); // 在中序遍历中的位置
        for (int i = 0; i < inorder.length; i++) {
            indices.put(inorder[i], i);
        }
        int rootIndex = indices.get(preorder[0]);
        // 通过中序遍历顺序建立索引树
        TreeNode root = new TreeNode(rootIndex);
        for (int i = 1; i < preorder.length; i++) {
            int index = indices.get(preorder[i]);
            TreeNode n = root;
            while (true) {
                if (index < n.val) {
                    if (n.left == null) {
                        n.left = new TreeNode(index);
                        break;
                    } else {
                        n = n.left;
                    }
                } else {
                    if (n.right == null) {
                        n.right = new TreeNode(index);
                        break;
                    } else {
                        n = n.right;
                    }
                }
            }
        }
        // 通过索引树恢复原值
        recover(root, inorder);
        return root;
    }

    private void recover(TreeNode node, int[] inorder) {
        node.val = inorder[node.val];
        if (node.left != null) recover(node.left, inorder);
        if (node.right != null) recover(node.right, inorder);
    }

    /**
     * LeetCode官方解答思路：
     * 对于任意一颗树而言，前序遍历的形式总是：
     * [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
     * 即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是：
     * [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indices = new HashMap<>(); // 在inorder中的位置
        for (int i = 0; i < inorder.length; i++) {
            indices.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, indices);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder,
                               int preStart, int preEnd,
                               int inStart, int inEnd,
                               Map<Integer, Integer> indices) {
        // 对于前序遍历，根节点是第一个被遍历到的
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);
        int inRootIndex = indices.get(rootValue); // 根节点在inorder中的序号
        int leftTreeLen = inRootIndex - inStart; // 左子树长度
        int rightTreeLen = inEnd - inRootIndex; // 右子树长度
        if (leftTreeLen != 0) root.left = buildTree(preorder, inorder,
                preStart + 1, preStart + leftTreeLen, inStart, inRootIndex - 1, indices); // 左子树
        if (rightTreeLen != 0) root.right = buildTree(preorder, inorder,
                preStart + leftTreeLen + 1, preEnd, inRootIndex + 1, inEnd, indices); // 右子树
        return root;
    }
}
