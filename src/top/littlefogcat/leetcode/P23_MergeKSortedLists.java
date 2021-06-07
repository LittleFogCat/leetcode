package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class P23_MergeKSortedLists {

    // 排序
    public ListNode mergeKLists1(ListNode[] lists) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (ListNode ln : lists) {
            while (ln != null) {
                arr.add(ln.val);
                ln = ln.next;
            }
        }
        if (arr.size() == 0) return null;
        Collections.sort(arr);
        ListNode r = new ListNode(arr.get(0));
        ListNode p = r;
        for (int i = 1; i < arr.size(); i++) {
            p.next = new ListNode(arr.get(i));
            p = p.next;
        }
        return r;
    }

    //-------------------------分割线----------------------

    // 使用归并排序
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        merge(lists, lists.length);
        return lists[0];
    }

    private void merge(ListNode[] lists, int length) {
        if (length == 1) return; // 结束
        // 两两归并，序号 x 与 x + len/2 归并
        for (int i = 0; i < length / 2; i++) {
            lists[i] = merge(lists[i], lists[i + length / 2]);
        }
        if ((length & 1) == 1) { // 奇数项
            lists[length / 2] = lists[length - 1];
        }
        merge(lists, (length + 1) / 2);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        return head.next;
    }
}
