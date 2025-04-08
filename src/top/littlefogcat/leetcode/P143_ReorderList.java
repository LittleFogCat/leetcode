package top.littlefogcat.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 题意：把序号为 x 的节点插入到 n-x 之后
 * <p>
 * 思路：使用一个栈，将后 n/2 的节点压入栈，然后原节点和栈中节点依次排列
 */
public class P143_ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) return;
        int n = 0; // 链表长度
        ListNode p = head;
        Queue<ListNode> queue = new ArrayDeque<>(); // 正向排列节点
        Stack<ListNode> stack = new Stack<>(); // 逆向排列节点
        while (p != null) {
            queue.offer(p);
            stack.push(p);
            p = p.next;
            n++;
        }
        ListNode prevHead = new ListNode(0); // 头节点之前的节点
        p = prevHead;
        for (int i = 0; i < n; i++, p = p.next) {
            ListNode next = (i & 1) == 0 ? queue.poll() : stack.pop();
            System.out.println("next: " + next.val);
            p.next = next;
        }
        p.next = null;
    }

    public void reorderList1(ListNode head) {
        if (head == null) return;
        int n = 0; // 链表长度
        ListNode p = head;
        while (p != null) {
            p = p.next;
            n++;
        }
        ListNode[] queue = new ListNode[n]; // 正向排列节点
        ListNode[] stack = new ListNode[n]; // 逆向排列节点
        p = head;
        for (int i = 0; i < n; i++, p = p.next) {
            queue[i] = p;
            stack[n - i - 1] = p;
        }
        ListNode prevHead = new ListNode(0); // 头节点之前的节点
        p = prevHead;
        for (int i = 0; i < n; i++, p = p.next) {
            ListNode next = (i & 1) == 0 ? queue[i / 2] : stack[i / 2];
//            System.out.println("next: " + next.val);
            p.next = next;
        }
        p.next = null;
    }

    /**
     * 旧序号 -> 新序号
     * 0 -> 0
     * 1 -> 2
     * 2 -> 4
     * ……
     * n-3 -> 5
     * n-2 -> 3
     * n-1 -> 1
     * <p>
     * 新序号 -> 旧序号
     * 0 -> 0
     * 1 -> n-1
     * 2 -> 1
     * 3 -> n-2
     * 4 -> 2
     * 5 -> n-3
     * ……
     * i -> i%2 == 0 ? i/2 : n-i/2-1
     * ……
     */
    public void reorderList2(ListNode head) {
        if (head == null) return;
        int n = 0; // 链表长度
        ListNode p = head;
        while (p != null) {
            p = p.next;
            n++;
        }
        ListNode[] linkedList = new ListNode[n];
        p = head;
        for (int i = 0; i < n; i++, p = p.next) {
            linkedList[i] = p;
        }
        p = head;
        for (int i = 1; i < n; i++, p = p.next) {
            p.next = linkedList[i % 2 == 0 ? i / 2 : n - i / 2 - 1];
        }
        p.next = null;
    }
}
