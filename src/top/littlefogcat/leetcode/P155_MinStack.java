package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P155_MinStack {
    class MinStack1 {
        private final List<Integer> list = new ArrayList<>();
        private Integer min = null;

        public void push(int x) {
            if (min == null || min > x) min = x;
            list.add(x);
        }

        public void pop() {
            int last = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            if (last == min) {
                if (list.size() == 0) min = null;
                else {
                    min = list.get(0);
                    for (int i = 1; i < list.size(); i++) {
                        if (list.get(i) < min) {
                            min = list.get(i);
                        }
                    }
                }
            }
        }

        public int top() {
            return list.get(list.size() - 1);
        }

        public int getMin() {
            return min;
        }
    }

    class MinStack0 {
        private Stack<Integer> data = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        public void push(int x) {
            data.push(x);
            if (minStack.isEmpty()) minStack.push(x);
            else {
                int min = minStack.peek();
                minStack.push(Math.min(min, x));
            }
        }

        public void pop() {
            data.pop();
            minStack.pop();
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    class MinStack {
        private List<Integer> data = new ArrayList<>();
        private List<Integer> minStack = new ArrayList<>();
        private int size = 0;

        public void push(int x) {
            data.add(x);
            if (minStack.isEmpty()) minStack.add(x);
            else {
                int min = minStack.get(size - 1);
                minStack.add(Math.min(min, x));
            }
            size++;
        }

        public void pop() {
            data.remove(size - 1);
            minStack.remove(size - 1);
            size--;
        }

        public int top() {
            return data.get(size - 1);
        }

        public int getMin() {
            return minStack.get(size - 1);
        }
    }
}
