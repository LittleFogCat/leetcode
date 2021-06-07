package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P236_LowestCommonAncestorOfABinaryTree {
    private List<TreeNode> pParent; // p的祖先列表
    private List<TreeNode> qParent; // q的祖先列表

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(new ArrayList<>(), root, p, q); // 深度优先搜索找到p、q节点
        int i;
        for (i = 0; i < pParent.size() && i < qParent.size(); i++) {
            TreeNode pNode = pParent.get(i);
            TreeNode qNode = qParent.get(i);
            if (pNode != qNode) break;
        }
        return pParent.get(i - 1);
    }

    private void find(ArrayList<TreeNode> parents, TreeNode node, TreeNode p, TreeNode q) {
        parents.add(node);
        if (node == p) {
            pParent = new ArrayList<>(parents);
        }
        if (node == q) {
            qParent = new ArrayList<>(parents);
        }
        if (pParent != null && qParent != null) return; // 都找到了，不继续
        if (node.left != null) find(parents, node.left, p, q);
        if (node.right != null) find(parents, node.right, p, q);
        parents.remove(parents.size() - 1);
    }
}
