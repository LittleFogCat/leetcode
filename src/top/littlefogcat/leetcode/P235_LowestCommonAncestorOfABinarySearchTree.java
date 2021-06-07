package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P235_LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return root.val > p.val && root.val > q.val ? lowestCommonAncestor(root.left, p, q)
                : root.val < p.val && root.val < q.val ? lowestCommonAncestor(root.right, p, q)
                : root;
    }

    //-----------------------------------------
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Traverse tr = new Traverse(root, p, q);
        int i = 0;
        for (; i < tr.pList.size() && i < tr.qList.size() && tr.pList.get(i) == tr.qList.get(i); i++) ;
        return tr.pList.get(i - 1);
    }

    private static class Traverse {
        TreeNode root;
        TreeNode p;
        TreeNode q;
        List<TreeNode> current = new ArrayList<>();
        List<TreeNode> pList = new ArrayList<>();
        List<TreeNode> qList = new ArrayList<>();
        int found = 0;

        public Traverse(TreeNode root, TreeNode p, TreeNode q) {
            this.root = root;
            this.p = p;
            this.q = q;
            traverse(root);
        }

        void traverse(TreeNode node) {
            if (found == 2) return;
            current.add(node);
            doInTraverse(node);
            if (node.left != null) traverse(node.left);
            if (node.right != null) traverse(node.right);
            current.remove(current.size() - 1);
        }

        void doInTraverse(TreeNode node) {
            if (node == p) {
                found++;
                pList.addAll(current);
            } else if (node == q) {
                found++;
                qList.addAll(current);
            }
        }
    }

    /// ------------------------------------------------------------

    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] pAnc = ancestors(root, p);
        TreeNode[] qAnc = ancestors(root, q);
        int i;
        for (i = 0; i < pAnc.length && i < qAnc.length && pAnc[i] == qAnc[i]; i++) ;
        return pAnc[i - 1];
    }

    // 从root开始，找出一个节点的所有祖先
    TreeNode[] ancestors(TreeNode root, TreeNode node) {
        TreeNode[] ancestors = new TreeNode[1024];
        findAncestors(ancestors, root, node, 0);
        return ancestors;
    }

    private boolean findAncestors(TreeNode[] ancestors, TreeNode root, TreeNode node, int current) {
        if (root == null) return false;
        ancestors[current] = root;
        if (root == node) return true;
        return findAncestors(ancestors, root.left, node, current + 1)
                || findAncestors(ancestors, root.right, node, current + 1);
    }
}
