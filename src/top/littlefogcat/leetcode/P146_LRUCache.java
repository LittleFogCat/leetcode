package top.littlefogcat.leetcode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import top.littlefogcat.leetcode.utils.GSON;
import top.littlefogcat.leetcode.utils.ReflectUtil;
import top.littlefogcat.leetcode.utils.TestCase;
import top.littlefogcat.leetcode.utils.TestCaseParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class P146_LRUCache {
    class LRUCache {
        private int capacity;
        private Map<Integer, LRUNode> innerMap; // K-V存储
        private LRUDeque innerQueue = new LRUDeque(); // LRU队列

        private class LRUDeque {
            LRUNode head;
            LRUNode tail;
            int size;

            void addLast(LRUNode node) {
                if (head == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                    tail.next = null;
                }
                size++;
            }

            int size() {
                return size;
            }

            LRUNode removeFirst() {
                if (head == null) return null;
                if (head.next == null) {
                    LRUNode r = head;
                    head = null;
                    tail = null;
                    size = 0;
                    return r;
                } else {
                    LRUNode newHead = head.next;
                    LRUNode oldHead = head;
                    newHead.prev = null;
                    head = newHead;
                    size--;
                    return oldHead;
                }
            }

            void remove(LRUNode node) {
                if (node.prev == null && node.next == null) return;
                if (node.prev == null) { // head
                    removeFirst();
                    return;
                }
                if (node.next == null) { // tail
                    LRUNode newTail = tail.prev;
                    newTail.next = null;
                    tail = newTail;
                } else { // normal
                    LRUNode prev = node.prev;
                    LRUNode next = node.next;
                    node.prev = null;
                    node.next = null;
                    prev.next = next;
                    next.prev = prev;
                }
                size--;
            }

            @Override
            public String toString() {
                if (head == null) return "[]";
                return "[" + head.toString() + "]";
            }
        }

        private class LRUNode {
            int key;
            int value;
            LRUNode prev;
            LRUNode next;

            @Override
            public String toString() {
                return "[" + key + "," + value + "]"
                        + (next == null ? "" : "->" + next.toString());
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.innerMap = new HashMap<>(capacity);
        }

        public int get(int key) {
            if (!innerMap.containsKey(key)) {
                return -1;
            }
            LRUNode node = innerMap.get(key);
            onItemUsed(node);
            return node.value;
        }

        public void put(int key, int value) {
            // 如果已经存在，改变value即可
            if (innerMap.containsKey(key)) {
                LRUNode node = innerMap.get(key);
                node.value = value;
                onItemUsed(node);
            } else {
                // 如果插入新数据达到容量上限，删除最近最少使用的项
                if (innerQueue.size() >= capacity) {
                    LRUNode delete = innerQueue.removeFirst();
                    innerMap.remove(delete.key);
                }
                LRUNode newNode = new LRUNode();
                newNode.key = key;
                newNode.value = value;
                innerMap.put(key, newNode);
                innerQueue.addLast(newNode);
            }
        }

        private void onItemUsed(LRUNode node) {
            innerQueue.remove(node);
            innerQueue.addLast(node);
        }

        @Override
        public String toString() {
            return innerQueue.toString() + ", size=" + innerQueue.size;
        }
    }

    /**
     * ["LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get"]
     * [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1]]
     *
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String command = "[\"LRUCache\",\"put\",\"put\",\"put\",\"put\",\"put\",\"get\",\"put\",\"get\",\"get\",\"put\",\"get\",\"put\",\"put\",\"put\",\"get\",\"put\",\"get\",\"get\",\"get\",\"get\",\"put\",\"put\",\"get\",\"get\",\"get\",\"put\",\"put\",\"get\",\"put\",\"get\",\"put\",\"get\"]\n" +
                "[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1]]";
        TestCase testCase = parseCommand(command);
//        System.out.println(lruCache.get(2)); // get 2 []
//        lruCache.put(2, 6); // put 2,6 [2,6]
//        System.out.println(lruCache.get(1)); // get 1 [2,6]
//        lruCache.put(1, 5); // put 1,5 [2,6 1,5]
//        lruCache.put(1, 2); // put 1,2 []
//        System.out.println(lruCache.get(1)); // get 1
//        System.out.println(lruCache.get(2)); // get 2
        List<String> cmdList = (List<String>) testCase.commandList;
        List<List<Integer>> paramList = (List<List<Integer>>) testCase.paramList;
        LRUCache lruCache = new P146_LRUCache().new LRUCache(paramList.get(0).get(0));
        for (int i = 1; i < cmdList.size(); i++) {
            String cmd = cmdList.get(i);
            List<Integer> params = paramList.get(i);
            System.out.print(cmd + ": " + params + "  -->  ");
            Class[] paramTypes = new Class[params.size()];
            for (int i1 = 0; i1 < params.size(); i1++) {
                paramTypes[i1] = ReflectUtil.unbox(params.get(i1).getClass());
            }
            Method m = LRUCache.class.getDeclaredMethod(cmd, paramTypes);
            Object ret = m.invoke(lruCache, params.toArray());
            System.out.println(ret + "  -->  " + lruCache);
        }
    }


    private static TestCase parseCommand(String cmdString) {
        String[] cap = cmdString.split("\n");
        List<String> cmdList = GSON.fromJson(cap[0]);
        List<List<Integer>> paramList = GSON.fromJson(cap[1], new TypeToken<List<List<Integer>>>() {
        }.getType());
        if (cmdList.size() != paramList.size()) {
            String errorMsg = "Size Not Equal! " +
                    "cmd=" + cmdList.size() + ",param=" + paramList.size();
            throw new IllegalStateException(errorMsg);
        }
        TestCase testCase = new TestCase();
        testCase.commandList = cmdList;
        testCase.paramList = paramList;
        return testCase;
    }
}
