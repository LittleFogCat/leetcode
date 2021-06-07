package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P330_PatchingArray_ {
    public int minPatches(int[] nums, int n) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> -a));
        int count = 0;
        if (nums.length == 0) {
            nums = new int[]{1};
            count = 1;
        }
        int max = nums[0];
        for (int num : nums) {
            queue.add(num);
            if (num > max) max = num;
        }
        for (int i = 1; i <= Math.min(max, n); ) {
            if (cannotBeFormed(i, queue)) {
                queue.add(i);
                count++;
                i *= 2;
            } else i++;
        } // 到现在为止，1~max都可以组成了。找出下一个不能组成的数，那么就继续加入。
        long next = -1;
        int last = 0;
        for (Object o : queue.toArray()) last += (int) o;
        for (int i = max + 1; i <= last; i++) {
            if (cannotBeFormed(i, queue)) {
                next = i;
                break;
            }
        }
//        System.out.println("下一个：" + next);
        if (next == -1) return count;

        while (next <= n) { // n不能组成
            next *= 2L;
            count++;
        }
//        System.out.println(queue);
        return count;
    }

    private boolean cannotBeFormed(int n, PriorityQueue<Integer> queue) {
        PriorityQueue<Integer> copy = new PriorityQueue<>(queue);
        while (copy.size() > 0) {
            int poll = copy.poll();
            if (n == poll) return false;
            if (poll < n) n -= poll;
        }
        return true;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        priorityQueue.addAll(Arrays.asList(1, 2, 6, 3, 12, 5, 4));
        while (priorityQueue.size() > 0)
            System.out.println(priorityQueue.poll());
    }
}
