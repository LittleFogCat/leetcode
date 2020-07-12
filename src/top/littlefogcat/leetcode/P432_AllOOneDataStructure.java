package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P432_AllOOneDataStructure {
    static class AllOne {

        class LinkedHashMap {
            class ListNode {
                ListNode prev;
                ListNode next;
                int val;
                String key;

                public ListNode(int val, String key) {
                    this.val = val;
                    this.key = key;
                }

                @Override
                public String toString() {
                    return "(" + key + ", " + val + ") -> " + next;
                }
            }

            private final Map<String, ListNode> innerMap = new HashMap<>();
            private final ListNode head = new ListNode(0, null); // 链表头，防空指针
            private final ListNode tail = new ListNode(0, null); // 链表尾，防空指针

            LinkedHashMap() {
                // 初始化链表
                head.next = tail;
                tail.prev = head;
            }

            int size() {
                return innerMap.size();
            }

            ListNode first() {
                return size() == 0 ? null : head.next;
            }

            ListNode last() {
                return size() == 0 ? null : tail.prev;
            }

            void inc(String key) {
                if (!innerMap.containsKey(key)) {
                    addNewKey(key);
                    return;
                }
                ListNode node = innerMap.get(key);
                int newVal = node.val + 1;
                ListNode last = node; // 最后一个值为val的
                while (last.next.val == last.val) {
                    last = last.next;
                }
                node.val = newVal;
                if (last != node) { // 移动到last之后
                    linkedListDelete(node);
                    linkedListInsert(last, node);
                }
            }

            void dec(String key) {
                if (!innerMap.containsKey(key)) return;
                ListNode node = innerMap.get(key);
                if (node.val == 1) {
                    innerMap.remove(key);
                    linkedListDelete(node);
                } else {
                    int newVal = node.val - 1;
                    ListNode first = node; // 第一个值为val的
                    while (first.prev.val == first.val) {
                        first = first.prev;
                    }
                    node.val = newVal;
                    if (first != node) {
                        linkedListDelete(node);
                        linkedListInsert(first.prev, node);
                    }
                }
            }

            ListNode remove(String key) {
                if (!innerMap.containsKey(key)) return null;
                ListNode removed = innerMap.remove(key);
                linkedListDelete(removed);
                return removed;
            }

            private void addNewKey(String key) {
                if (innerMap.containsKey(key)) return;
                ListNode node = new ListNode(1, key);
                linkedListInsert(head, node);
                innerMap.put(key, node);
            }

            private void linkedListInsert(ListNode prev, ListNode insert) {
                insert.next = prev.next;
                insert.prev = prev;
                prev.next.prev = insert;
                prev.next = insert;
            }

            private void linkedListDelete(ListNode delete) {
                delete.prev.next = delete.next;
                delete.next.prev = delete.prev;
            }

            @Override
            public String toString() {
                return head.toString();
            }
        }

        private final LinkedHashMap map = new LinkedHashMap();

        /**
         * Initialize your data structure here.
         */
        public AllOne() {

        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         */
        public void inc(String key) {
            map.inc(key);
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         */
        public void dec(String key) {
            map.dec(key);
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            LinkedHashMap.ListNode maxNode = map.last();
            return maxNode == null ? "" : maxNode.key;
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            LinkedHashMap.ListNode minNode = map.first();
            return minNode == null ? "" : minNode.key;
        }

        @Override
        public String toString() {
            return map.toString();
        }
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("abc");
        allOne.inc("cc");
        allOne.inc("cac");
        allOne.inc("cac");
        allOne.inc("cac");
        allOne.inc("cc");

        System.out.println(allOne);
    }

}
