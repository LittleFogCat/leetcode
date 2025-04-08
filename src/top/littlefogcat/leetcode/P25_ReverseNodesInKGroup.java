package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.Collections;
import java.util.LinkedList;

public class P25_ReverseNodesInKGroup {

    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        ListNode h = new ListNode(0);
        ListNode tail = h;
        LinkedList<ListNode> stack = new LinkedList<>();
        while (head != null) {
            int count = k;
            while (count > 0) {
                if (head == null) break;
                ListNode p = head;
                head = head.next;
                stack.push(p);
                count--;
            }
            if (count > 0) Collections.reverse(stack);
            while (stack.size() > 0) {
                tail.next = stack.pop();
                tail = tail.next;
            }
        }
        if (tail != null) tail.next = null;

        return h.next;
    }

    private class Stack {
        private ListNode head;
        private int size = 0;

        void push(ListNode node) {
            size++;
            node.next = head;
            head = node;
        }

        ListNode pop() {
            size--;
            ListNode delete = head;
            head = head.next;
            return delete;
        }

        void reverse() {
            Stack temp = new Stack();
            int s = size();
            while (size() > 0) {
                temp.push(pop());
            }
            head = temp.head;
            size = s;
        }

        int size() {
            return size;
        }

        @Override
        public String toString() {
            return head.toString();
        }
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        ListNode h = new ListNode(0);
        ListNode tail = h;
        Stack stack = new Stack();
        while (head != null) {
            int count = k;
            while (count > 0) {
                if (head == null) break;
                ListNode p = head;
                head = head.next;
                stack.push(p);
                count--;
            }
            if (count > 0) stack.reverse();
            while (stack.size() > 0) {
                tail.next = stack.pop();
                tail = tail.next;
            }
        }
        if (tail != null) tail.next = null;

        return h.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null || head.next == null) return head;
        ListNode root = new ListNode(0); // 指向头节点的节点
        root.next = head;
        ListNode p = root; // 指向每一轮循环的头节点
        while (checkListRemainK(p.next, k)) {
            p = reverse(p, k);
        }
        return root.next;
    }

    /**
     * 如果链表剩余元素大于等于k个，返回true，否则返回false
     * 如果链表剩余元素小于k，那么就不翻转了
     */
    private boolean checkListRemainK(ListNode head, int k) {
        if (head == null) return false;
        if (k == 1) return true;
        int count = 1;
        while ((head = head.next) != null) {
            count++;
            if (count == k) return true;
        }
        return false;
    }

    /**
     * 翻转链表。
     * 对于一个链表，从第二项开始，把接下节点依次插入到链表的头部，最后得到的就是翻转之后的链表了。
     * 返回翻转后的链表的最后一个节点，也就是翻转前链表的第一个节点。
     *
     * @param h 链表头节点的前一节点
     * @param k 链表的长度
     * @return 翻转后链表的最后一个节点
     */
    private ListNode reverse(ListNode h, int k) {
        if (k == 1 || h.next == null || h.next.next == null) return h.next;
        ListNode p = h.next;
        int c = k - 1; // 移动的次数，总共需要移动k-1次
        while (c-- > 0) {
            ListNode delete = p.next; // 需要移动的节点
            p.next = p.next.next; // 删除节点
            delete.next = h.next; // 插入节点
            h.next = delete; // 插入节点
        }
        return p;
    }


    public static void main(String[] args) {
        P25_ReverseNodesInKGroup p = new P25_ReverseNodesInKGroup();
        ListNode list = Util.linkedList(1, 2, 3, 4, 5);
        System.out.println(p.reverseKGroup(list, 3));
    }
}
