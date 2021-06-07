package top.littlefogcat.leetcode;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class P432_AllOOneDataStructure {

    public class AllOne1 {
        private final Map<String, Integer> map = new HashMap<>();

        public void inc(String key) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        public void dec(String key) {
            if (!map.containsKey(key)) return;
            int val = map.get(key);
            if (val == 1) map.remove(key);
            else map.put(key, val - 1);
        }

        public String getMaxKey() {
            String maxKey = "";
            int max = 0;
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() > max) {
                    maxKey = e.getKey();
                    max = e.getValue();
                }
            }
            return maxKey;
        }

        public String getMinKey() {
            String minKey = "";
            int min = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() < min) {
                    minKey = e.getKey();
                    min = e.getValue();
                }
            }
            return minKey;
        }
    }

    public class AllOne {
        /**
         * k-v查找节点
         */
        private final Map<String, ListNode> map = new HashMap<>();
        /**
         * key - 节点的值；
         * value - 链表中第一个值为key的节点。
         */
        private final Map<Integer, ListNode> first = new HashMap<>();
        /**
         * key - 节点的值；
         * value - 链表中最后一个值为key的节点。
         */
        private final Map<Integer, ListNode> last = new HashMap<>();

        /**
         * 链表伪头节点
         */
        private final ListNode head = new ListNode(null, 0);
        /**
         * 链表伪尾节点
         */
        private final ListNode tail = new ListNode(null, 0);

        AllOne() {
            head.next = tail;
            tail.prev = head;
            head.head = tail.head = head;
            head.tail = tail.tail = tail;
        }

        private class ListNode { // 链表节点
            ListNode head, tail; // 方便打印
            ListNode prev, next;
            String key;
            int val;

            public ListNode(String key, int val) {
                this.key = key;
                this.val = val;
            }

            public ListNode(ListNode head, ListNode tail, String key, int val) {
                this.head = head;
                this.tail = tail;
                this.key = key;
                this.val = val;
            }

            @Override
            public String toString() {
                if (this == tail) return "tail";
                if (this == head) return "head -> " + next;
                return "(" + key + ": " + val + ") -> " + next;
            }

            public String toReverseString() {
                if (this == head) return "head";
                if (this == tail) return "tail <- " + prev.toReverseString();
                return "(" + key + ": " + val + ") <- " + prev.toReverseString();
            }
        }

        /**
         * 将节点 [insert] 插入到 n1 与 n2 之间
         */
        private void insert(ListNode n1, ListNode n2, ListNode insert) {
            n1.next = insert;
            n2.prev = insert;
            insert.prev = n1;
            insert.next = n2;
        }

        /**
         * 删除节点[n]
         */
        private void remove(ListNode n) {
            ListNode prev = n.prev;
            ListNode next = n.next;
            prev.next = next;
            next.prev = prev;
            n.prev = null;
            n.next = null;
        }

        /**
         * 将node移动到prev与next之间
         */
        private void move(ListNode node, ListNode prev, ListNode next) {
            remove(node);
            insert(prev, next, node);
        }

        /**
         * 将[node]设置为新的val值起始点
         */
        private void newFirst(int val, ListNode node) {
            first.put(val, node);
            if (!last.containsKey(val)) last.put(val, node);
        }

        /**
         * 将[node]设置为新的val值终止点
         */
        private void newLast(int val, ListNode node) {
            last.put(val, node);
            if (!first.containsKey(val)) first.put(val, node);
        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         * <p>
         * 如果当前key不存在，那就把这个节点插入到链表尾部.
         */
        public void inc(String key) {
            if (!map.containsKey(key)) { // 插入到链表末尾
                ListNode node = new ListNode(head, tail, key, 1);
                map.put(key, node);
                insert(tail.prev, tail, node);
                if (!first.containsKey(1)) newFirst(1, node);
                newLast(1, node);
            } else {
                ListNode node = map.get(key); // 当前节点
                int val = node.val; // 旧值
                int newVal = val + 1; // 新值
                ListNode firstNode = first.get(val); // 链表中第一个值为val的节点
                ListNode lastNode = last.get(val); // 链表中最后一个值为val的节点

                // 1. 找位置
                node.val = newVal;
                if (firstNode == lastNode) { // 当前节点是唯一一个值为val的节点
                    first.remove(val); // 没有值为val的节点了
                    last.remove(val);
                    newLast(newVal, node);
                } else if (node == firstNode) { // 该节点是链表中第一个值为val的节点
                    // 不动
                    newLast(newVal, node);
                    newFirst(val, node.next);
                } else {
                    if (node == lastNode) newLast(val, node.prev); // 是最后一个值val的节点
                    // 这个时候，节点应该移动到链表中第一个值为val的节点之前
                    move(node, firstNode.prev, firstNode);
                    newLast(newVal, node);
                }
            }
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         * <p>
         * 要考虑几点：
         * - 更新map
         * - 更新链表
         * - 更新first与last
         * <p>
         * 值减一之后，会往右移动
         */
        public void dec(String key) {
            // 与inc类似，不过多了一个值为1删除的判断
            ListNode node = map.get(key);
            if (node == null) return;

            int val = node.val;
            int newVal = val - 1;
            ListNode firstNode = first.get(val);
            ListNode lastNode = last.get(val);

            if (val == 1) { // 删除这个节点
                if (firstNode == lastNode) {
                    first.remove(1);
                    last.remove(1);
                } else if (node == firstNode) {
                    first.put(1, node.next);
                } else if (node == lastNode) {
                    last.put(1, node.prev);
                }
                remove(node);
                map.remove(key);
            } else {
                node.val = newVal;
                if (firstNode == lastNode) { // 唯一值为val的节点
                    // 位置不变，成为newVal的首位
                    first.remove(val);
                    last.remove(val);
                    newFirst(newVal, node);
                } else if (node == lastNode) { // 是最后一项val值的节点
                    // 位置不变，成为newVal的首位，并且prev成为val的最后一位
                    newFirst(newVal, node);
                    newLast(val, node.prev);
                } else {
                    if (node == firstNode) newFirst(val, node.next); // 是第一项val值的节点
                    move(node, lastNode, lastNode.next); // 移动到lastNode之后
                    newFirst(newVal, node);
                }
            }
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            return head.next == tail ? "" : head.next.key;
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            return tail.prev == head ? "" : tail.prev.key;
        }
    }

    public static void main(String[] args) {
        AllOne allOne = new P432_AllOOneDataStructure().new AllOne();
        allOne.inc("abc");
        allOne.inc("cc");
        allOne.inc("cac");
        allOne.inc("cac");
        allOne.inc("cac");
        allOne.inc("cc");

        System.out.println(allOne);
    }

}
