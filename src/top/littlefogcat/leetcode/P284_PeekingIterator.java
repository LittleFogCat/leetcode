package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class P284_PeekingIterator {
    class PeekingIterator implements Iterator<Integer> {
        private int[] values;
        private int cursor;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            List<Integer> l = new ArrayList<>();
            while (iterator.hasNext()) {
                l.add(iterator.next());
            }
            values = new int[l.size()];
            for (int i = 0; i < values.length; i++) {
                values[i] = l.get(i);
            }
            cursor = 0;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return hasNext() ? values[cursor] : null;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return hasNext() ? values[cursor++] : null;
        }

        @Override
        public boolean hasNext() {
            return cursor < values.length;
        }
    }
}
