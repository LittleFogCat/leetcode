package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P109_ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        List<Integer> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }
        int[] a = new int[arr.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = arr.get(i);
        }
        return convert(a, 0, a.length - 1);
    }

    private TreeNode convert(int[] a, int start, int end) {
        if (start == end) return new TreeNode(a[start]);
        int rootIdx = (end + start) / 2;
        TreeNode root = new TreeNode(a[rootIdx]);
        if (start < rootIdx) root.left = convert(a, start, rootIdx - 1);
        if (rootIdx < end) root.right = convert(a, rootIdx + 1, end);
        return root;
    }

    // --------------------------------------------------------------

    /**
     * 中序遍历：[[左子树], 根, [右子树]]
     */
    public TreeNode sortedListToBST1(ListNode head) {
        if (head == null) return null;
        ListNode h = head;
        int size = 0;
        do {
            size++;
        } while ((h = h.next) != null);
        return build(new ListNode[]{head}, 0, size - 1);
    }

    private TreeNode build(ListNode[] head, int start, int end) {
        if (start > end || head[0] == null) return null;
        int mid = (start + end) / 2;
        TreeNode left = build(head, start, mid - 1);
        TreeNode root = new TreeNode(head[0].val);
        root.left = left;
        head[0] = head[0].next;
        root.right = build(head, mid + 1, end);
        return root;
    }
}
