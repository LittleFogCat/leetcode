package top.littlefogcat.leetcode;

/**
 * 思路：普通插入排序 + 记录尾节点，如果大于直接插入到尾部。
 */
public class P147_InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        ListNode prevHead = new ListNode(0); // 伪头节点
        prevHead.next = head;
        ListNode tail = head; // 尾节点
        while (tail.next != null) {
            ListNode p = tail.next; // 待插入节点
            if (p.val > tail.val) { // 大于尾节点，直接插入到最后
                tail = tail.next;
            } else {
                tail.next = p.next;
                ListNode h = prevHead;
                while (h.next.val < p.val) { // 插入到第一个大于的节点之前
                    h = h.next;
                }
                p.next = h.next;
                h.next = p;
            }
        }
        return prevHead.next;
    }
}
