package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P60_PermutationSequence {
    // 0~9阶乘
    public static int[] jc = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    /**
     * 思路：从高位到低位依次找出对应排序的数字。
     *
     * 例如，n=3时，一共有3!=6种排列：
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     *
     * 其中百位1、2、3分别有2!=2种情况。
     * 通过这点，首先可以确定百位，然后递归依次确认低位即可。
     */
    public String getPermutation(int n, int k) {
        List<Integer> ns = new ArrayList<>();
        for (int i = 0; i < n; i++) ns.add(i + 1); // ns: 1~n 数字集合
        StringBuilder result = new StringBuilder(); // 保存结果
        getPermutation(ns, n, k - 1, result); // 第k ==> 序号为k-1
        return result.toString();
    }

    public void getPermutation(List<Integer> ns, int n, int k, StringBuilder result) {
        if (n == 0) return;
        int each = jc[n - 1]; // 每个数字打头有each种情况
        int index = k / each; // 第k大的数第一位是ns[index]
        int newK = k % each; // 下一个k
        int current = ns.get(index); // 当前位的数字
        ns.remove(index);
        result.append(current);
        getPermutation(ns, n - 1, newK, result);
    }
}
