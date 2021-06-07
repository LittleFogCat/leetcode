package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class P107_BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>(); // 返回值
        if (root == null) return r;
        LinkedList<TreeNode> queue = new LinkedList<>(); // 队列
        queue.offer(root);
        while (queue.size() > 0) {
            // 遍历一层
            TreeNode last = queue.getLast();
            List<Integer> line = new ArrayList<>();
            while (queue.size() > 0) {
                TreeNode node = queue.pollFirst();
                line.add(node.val);
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
                if (node == last) break;
            }
            r.add(line);
        }
        Collections.reverse(r);
        return r;
    }
}
