package top.littlefogcat.leetcode;

import static top.littlefogcat.leetcode.Util.arr;

public class P4_MedianOfTwoSortedArrays {
    /**
     * 题目要求时间复杂度log(m+n)，所以肯定不能遍历了，
     * 应参照binarySearch来进行查找。
     */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        // 找中位数也就是找到中间的那个数
        // 那么只需要找到第k小的数就可以了
        int k = (len - 1) / 2; // 需要找到第k小的数

        // 类似二分查找
        double dk = find(nums1, 0, nums2, 0, k);
        // 奇数直接返回k项，偶数和k+1项取平均数
        return (len & 1) == 1 ? dk :
                (dk + find(nums1, 0, nums2, 0, k + 1)) / 2.0;
    }

    private double find(int[] nums1, int p1, int[] nums2, int p2, int k) {
        // 需要找到第k小的这个数
        // 使用淘汰制，比较nums1[p1]和nums2[p2]，
        // 淘汰掉小的左边部分，因为他们不可能是中位数了。
        // 一次淘汰掉一半，则中位数在剩余的数里面，
        // 继续递归就可以找到中位数了
        if (p1 >= nums1.length) return nums2[p2 + k]; // nums1全部被淘汰了，直接从nums2中找
        if (p2 >= nums2.length) return nums1[p1 + k]; // nums2全部被淘汰了，直接从nums1中找
        if (k == 0) return Math.min(nums1[p1], nums2[p2]); // k==0只需要比较第一项就可以了
        // k!=0的情况，本轮淘汰掉out个数
        // out > 0
        int out = (k + 1) / 2;
        // nums1个数不够，直接从nums2中淘汰
        // 因为最终需要淘汰k项，而nums1的个数小于k/2，
        // 所以nums2的前k/2必然淘汰
        if (p1 + out > nums1.length) return find(nums1, p1, nums2, p2 + out, k - out);
        // nums2个数不够，直接从nums1中淘汰
        if (p2 + out > nums2.length) return find(nums1, p1 + out, nums2, p2, k - out);
        // 长度都够，那么找出两个数组中找出每组最后一个淘汰的数，并把小于其中较小的那个的数全部淘汰
        // 换句话说，找出其中一个数组，然后淘汰其前out项
        int out1 = nums1[p1 + out - 1];
        int out2 = nums2[p2 + out - 1];
        return out1 > out2 ? find(nums1, p1, nums2, p2 + out, k - out) :
                find(nums1, p1 + out, nums2, p2, k - out);
    }

}

    public static void main(String[] args) {
        P4_MedianOfTwoSortedArrays p4 = new P4_MedianOfTwoSortedArrays();
        Solution solution = p4.new Solution();
//        int[] n1 = new int[]{1, 2, 3, 4, 5}; // 3
//        int[] n2 = new int[]{1, 2, 3, 4, 5, 6}; // 3.5
//        print(solution.mid(n1)); // 3
//        System.out.println(solution.mid(n2)); // 3.5
//        System.out.println(solution.mid(n2, 1, 4)); // 3.5
//        System.out.println(solution.mid(n2, 1, 5)); // 4.0

        int[] n1, n2;

        n1 = arr(1);
        n2 = arr(2);
        assert 1.5 == solution.findMedianSortedArrays(n1, n2);

        n1 = arr(1, 2);
        n2 = arr(3, 4);
        assert 2.5 == solution.findMedianSortedArrays(n1, n2);

        n1 = arr(-2, -1);
        n2 = arr(3);
        assert -1 == solution.findMedianSortedArrays(n1, n2);
    }
}
