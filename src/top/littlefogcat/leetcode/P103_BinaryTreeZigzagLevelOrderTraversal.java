package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P103_BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        traverse(root, result, 0);
        for (int i = 0; i < result.size(); i++) {
            if (i % 2 == 1) Collections.reverse(result.get(i));
        }
        return result;
    }

    private void traverse(TreeNode node, List<List<Integer>> result, int level) {
        if (result.size() == level) result.add(new ArrayList<>());
        List<Integer> currentLevel = result.get(level);
        currentLevel.add(node.val);
        if (node.left != null) traverse(node.left, result, level + 1);
        if (node.right != null) traverse(node.right, result, level + 1);
    }
}
