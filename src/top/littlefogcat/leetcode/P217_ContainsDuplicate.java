package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P217_ContainsDuplicate {
    public boolean containsDuplicate0(int[] nums) {
        Set<Integer> intSet = new HashSet<>();
        for (int num : nums) {
            if (intSet.contains(num)) return true;
            intSet.add(num);
        }
        return false;
    }

    public boolean containsDuplicate00(int[] nums) {
        ArrayList<Integer> al = new ArrayList<>(nums.length);
        for (int num : nums) {
            al.add(num);
        }
        Set<Integer> set = new HashSet<>(al);
        return set.size() != al.size();
    }

    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        int size = 57078; // magic number
        int[] map = new int[size + 1];
        for (int num : nums) {
            int hash = (num & 0xFFFFFFF) % size;
            if (map[hash] == 1) {
                return true;
            } else map[hash] = 1;
        }
        return false;
    }

    public boolean containsDuplicate22(int[] nums) {
        int len = nums.length;
        int offset = 0;
        while (len > 0) {
            offset++;
            len >>= 1;
        }
        int mask = (1 << offset + 1) - 1;
        ListNode[] map = new ListNode[mask + 1];
        for (int num : nums) {
            int hash = num & mask;
            if (map[hash] == null) {
                map[hash] = new ListNode(num);
            } else {
                System.out.println("detect hash collision");
                for (ListNode head = map[hash]; ; head = head.next) {
                    if (head.val == num) return true;
                    if (head.next == null) break;
                }
            }
        }
        return false;
    }


    public boolean containsDuplicateByMergeSort(int[] nums) {
        int[] sorted = new int[nums.length];
        return mergeSort(nums, 0, nums.length, sorted);
    }

    /**
     * 归并排序
     */
    private boolean mergeSort(int[] nums, int start, int end, int[] sorted) {
        if (end - start <= 1) return false;
        int mid = (start + end) >> 1;
        return mergeSort(nums, start, mid, sorted) ||
                mergeSort(nums, mid, end, sorted) ||
                merge(nums, start, end, sorted);
    }

    private boolean merge(int[] nums, int start, int end, int[] sorted) {
        int mid = (start + end) >> 1;
        int i = start, j = mid;
        int k = 0;
        while (i < mid && j < end) {
            if (nums[i] == nums[j]) return true;
            else if (nums[i] < nums[j]) sorted[k++] = nums[i++];
            else if (nums[j] < nums[i]) sorted[k++] = nums[j++];
        }
        while (i < mid) sorted[k++] = nums[i++];
        while (j < end) sorted[k++] = nums[j++];
        System.arraycopy(sorted, 0, nums, start, k);
        return false;
    }

    public static void main(String[] args) {
        int min = -24500;
        int max = 30000;
        int[] arr = new int[max - min];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + min;
        }
        System.out.println(Arrays.toString(arr));
        P217_ContainsDuplicate p = new P217_ContainsDuplicate();
        p.containsDuplicateByMergeSort(arr);
    }
}
