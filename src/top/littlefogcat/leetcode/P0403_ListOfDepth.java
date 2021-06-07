package top.littlefogcat.leetcode;

import java.util.*;

public class P0403_ListOfDepth {
    public ListNode[] listOfDepth1(TreeNode tree) {
        List<List<TreeNode>> levels = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> tQueue = new LinkedList<>();
        queue.offer(tree);
        while (queue.size() > 0) {
            List<TreeNode> level = new ArrayList<>();
            while (queue.size() > 0) {
                TreeNode node = queue.poll();
                level.add(node);
                if (node.left != null) tQueue.offer(node.left);
                if (node.right != null) tQueue.offer(node.right);
            }
            levels.add(level);
            Queue<TreeNode> q = queue;
            queue = tQueue;
            tQueue = q;
        }

        ListNode[] r = new ListNode[levels.size()];
        for (int i = 0; i < r.length; i++) {
            ListNode head = new ListNode(0);
            ListNode p = head;
            List<TreeNode> level = levels.get(i);
            for (TreeNode node : level) {
                p.next = new ListNode(node.val);
                p = p.next;
            }
            r[i] = head.next;
        }
        return r;
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> nodeList = new ArrayList<>();

        // 广度优先遍历
        // 使用两个队列作为层次分割
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> tQueue = new LinkedList<>();
        queue.offer(tree);
        while (queue.size() > 0) { // 层次遍历
            ListNode head = new ListNode(0); // 链表
            ListNode p = head; // 链表
            while (queue.size() > 0) { // 建立层次链表，同时将下一层次添加到队列
                TreeNode node = queue.poll();
                p.next = new ListNode(node.val); // 链表
                p = p.next; // 链表
                if (node.left != null) tQueue.offer(node.left); // BFS
                if (node.right != null) tQueue.offer(node.right); // BFS
            }
            nodeList.add(head.next);
            // 交换双队列，重复利用
            Queue<TreeNode> q = queue;
            queue = tQueue;
            tQueue = q;
        }
        return nodeList.toArray(new ListNode[0]);
    }
}
