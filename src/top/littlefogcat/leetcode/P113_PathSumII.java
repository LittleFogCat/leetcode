package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 思路：DFS
 */
public class P113_PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> l = new ArrayList<>();
        sum(root, 0, targetSum, new ArrayList<>(), l);
        return l;
    }

    public void sum(TreeNode node, int currentSum, int targetSum, List<TreeNode> path, List<List<Integer>> l) {
        if (node == null) return;
        path.add(node);
        currentSum += node.val;
        if (node.left == null && node.right == null) {
            // 叶子节点
//            List<Integer> pathInt = path.stream()
//                    .map(treeNode -> treeNode.val)
//                    .collect(Collectors.toList());
//            System.out.println("path: " + pathInt + ", sum: " + currentSum);
            if (currentSum == targetSum) {
                List<Integer> pathInt = new ArrayList<>(path.size());
                for (TreeNode treeNode : path) {
                    pathInt.add(treeNode.val);
                }
                l.add(pathInt);
//                l.add(path.stream()
//                        .map(treeNode -> treeNode.val)
//                        .collect(Collectors.toList()));
            }
        } else {
            sum(node.left, currentSum, targetSum, path, l);
            sum(node.right, currentSum, targetSum, path, l);
        }
        path.remove(path.size() - 1);
    }
}
