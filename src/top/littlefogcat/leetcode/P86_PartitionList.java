package top.littlefogcat.leetcode;

public class P86_PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode h = new ListNode(0); // 链表的新头部
        ListNode split = new ListNode(0); // 分割节点的前一个节点
        ListNode p = new ListNode(0); // 指向当前节点
        p.next = h.next = split.next = head;
        while (split.next.val < x) {
            split = split.next;
            if (split.next == null) return head;
        }
        p = split;
//        System.out.println("初始：" + h);
        while (p.next != null) {
//            System.out.println("p.next: " + p.next.val);
            if (p.next.val < x) {
                // 删除错误的节点p.next
                ListNode delete = p.next;
                if (p.next == h.next) h.next = h.next.next;
                else p.next = p.next.next;
//                System.out.println("删除" + delete.val + "，当前" + h);
                // 添加到正确的位置
                delete.next = split.next;
                if (split.next == h.next) h.next = delete;
                split.next = delete;
//                System.out.println("插入" + delete.val + "到" + split.val + "之后" + "，当前" + h);
                split = split.next;
            } else p = p.next;
        }
        return h.next;
    }

    public static void main(String[] args) {
        P86_PartitionList p = new P86_PartitionList();
        ListNode r = p.partition(Util.linkedList(3, 1, 2), 3);
        System.out.println("最终结果：" + r);
    }
}
