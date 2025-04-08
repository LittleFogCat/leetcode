package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

public class P148_SortList {
    // 使用归并排序
    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode h = head;
        while (h != null) {
            len++;
            h = h.next;
        }
        return sortList(head, len);
    }

    // 递归
    private ListNode sortList(ListNode head, int len) {
        if (len <= 1) return head;
        ListNode mid = head;
        for (int i = 0; i < len / 2 - 1; i++) {
            mid = mid.next;
        }
        ListNode n = mid.next;
        mid.next = null;
        ListNode left = sortList(head, len / 2);
        ListNode right = sortList(n, len - len / 2);
        return merge(left, right);
    }

    // 合并
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (true) {
            if (l1 == null) {
                tail.next = l2;
                break;
            } else if (l2 == null) {
                tail.next = l1;
                break;
            }
            if (l1.val > l2.val) {
                tail.next = l2;
                l2 = l2.next;
            } else {
                tail.next = l1;
                l1 = l1.next;
            }
            tail = tail.next;
            tail.next = null;
        }
        return head.next;
    }

    public static void main(String[] args) {
        P148_SortList p = new P148_SortList();
//        ListNode l1 = Util.arrayToLinkedList(2, 3, 5);
//        ListNode l2 = Util.arrayToLinkedList(4, 6, 8);
//        ListNode merge = p.merge(l1, l2);
//        System.out.println(merge);

        ListNode ll = Util.linkedList(4, 2, 1, 3);
        ListNode sorted = p.sortList(ll);
        System.out.println(sorted);
    }

}
