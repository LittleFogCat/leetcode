package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 */
public class P19_RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        ListNode preDelete = new ListNode(0); // 删除preDelete.next
        preDelete.next = head;
        int size = 0;

        do { // 这里while放后面，使node从head开始遍历
            size++;
            if (size > n) preDelete = preDelete.next;
            if (node.next == null) { // 最后一位了
                if (preDelete.next == head) head = head.next; // 删除head
                else preDelete.next = preDelete.next.next; // 删除preDelete.next
            }
        } while ((node = node.next) != null);

        return head;
    }

    public static void main(String[] args) {
        P19_RemoveNthNodeFromEndOfList p19 = new P19_RemoveNthNodeFromEndOfList();
        ListNode head = Util.getLinkedListFromArray(1, 2, 3, 4, 5);
        int n = 2;
        p19.removeNthFromEnd(head, n);
        System.out.println(head);
    }
}
