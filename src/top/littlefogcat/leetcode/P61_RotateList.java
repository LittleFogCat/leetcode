package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P61_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        List<ListNode> linkedList = new ArrayList<>();
        ListNode n = head;
        while (n != null) {
            linkedList.add(n);
            n = n.next;
        }
        k = k % linkedList.size();
        if (k == 0) return head;
        int newHeadPos = linkedList.size() - k;
        ListNode newHead = linkedList.get(newHeadPos);
        ListNode newTail = linkedList.get(newHeadPos - 1);
        newTail.next = null;
        linkedList.get(linkedList.size() - 1).next = head;
        return newHead;
    }
}
