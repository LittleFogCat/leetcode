package top.littlefogcat.leetcode;

import java.util.Arrays;

public class P274_H_Index {

    /**
     * 对于 x 满足：至少发表了 x 篇引用数 >= x 的文章，
     * 找出 x 的最大值 h。
     * <p>
     * 对于升序数组，该描述等价于：
     * 对于 i 满足：arr.length - i + 1 >= arr[i]
     * 找到最小的 i 并返回 arr.length - i + 1。
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            int x = citations.length - i;
            int y = citations[i];
            // 含义：至少发表了 x 篇引用数 >= y 的文章。
            int hi = Math.min(x, y); // 含义：至少发表了 hi 篇引用数 >= hi 的文章。
            if (hi > h) h = hi;
        }
        return h;
    }

    /**
     * 从数组中，找到一个数 h，使得 >= h 的数至少有 h 个。返回 h 的最大值。
     *
     * 令 f(x) = 数组中大于等于 x 的数的个数。
     * 即找到满足 f(x) >= x 的最大的 x。
     */
    public int hIndex2(int[] arr) {
        int[] count = new int[arr.length + 1]; // h 最大能取到 n
        for (int num : arr) {
            count[Math.min(num, arr.length)]++;
        }
        int fx = 0; // 数组中大于等于 x 的数的个数
        for (int x = count.length - 1; x >= 0; x--) {
            // 逆序遍历
            fx += count[x]; // 数组中大于等于 x 的数的个数
            if (fx >= x) {
                return x;
            }
        }
        return 0;
    }

}
