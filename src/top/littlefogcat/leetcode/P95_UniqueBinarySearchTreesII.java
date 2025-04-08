package top.littlefogcat.leetcode;

import java.util.*;

/**
 * 思路：先任意取一个点为根节点，则数组左侧元素为左树，右侧为右树；再在其中任取根节点，依此循环。
 */
public class P95_UniqueBinarySearchTreesII {

    public List<TreeNode> generateTrees(int n) {
        int[] numbers = new int[n];
        for (int i = 1; i <= n; i++) {
            numbers[i - 1] = i;
        }

        return generate(numbers, 0, numbers.length - 1);
    }

    private List<TreeNode> generate(int[] numbers, int start, int end) {
        if (start == end) return Collections.singletonList(new TreeNode(numbers[start]));
        List<TreeNode> nodes = new ArrayList<>();
        // generate left
        // generate right
        for (int i = start; i <= end; i++) {
            // 以numbers[i]为根节点
            List<TreeNode> leftTrees = null;
            List<TreeNode> rightTrees = null;
            if (i != 0) {
                leftTrees = generate(numbers, start, i - 1);
            }
            if (i != end) {
                rightTrees = generate(numbers, i + 1, end);
            }
            if (leftTrees != null && !leftTrees.isEmpty() && rightTrees != null && !rightTrees.isEmpty()) {
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode root = new TreeNode(numbers[i]);
                        root.left = leftTree;
                        root.right = rightTree;
                        nodes.add(root);
                    }
                }
            } else if (leftTrees != null && !leftTrees.isEmpty()) {
                for (TreeNode leftTree : leftTrees) {
                    TreeNode root = new TreeNode(numbers[i]);
                    root.left = leftTree;
                    nodes.add(root);
                }
            } else if (rightTrees != null && !rightTrees.isEmpty()) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(numbers[i]);
                    root.right = rightTree;
                    nodes.add(root);
                }
            }
        }

        return nodes;
    }

}
