package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：常规迭代使用栈。这里使用链表。
 */
public class P144_BinaryTreePreorderTraversal {

    /**
     * 迭代：链表
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        ListNode head = new ListNode(root); // 链表头
        ListNode p = head; // 链表指针
        while (p != null) {
            if (p.val.right != null) insert(p, new ListNode(p.val.right)); // 插入右儿子
            if (p.val.left != null) insert(p, new ListNode(p.val.left)); // 插入左儿子
            p = p.next; // 继续遍历
        }
        // 将链表转换成List
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val.val);
            head = head.next;
        }
        return result;
    }

    void insert(ListNode node, ListNode toInsert) {
        toInsert.next = node.next;
        node.next = toInsert;
    }

    class ListNode {
        TreeNode val;
        ListNode next;

        ListNode(TreeNode n) {
            val = n;
        }
    }

}
