package top.littlefogcat.leetcode.剑指offer;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class 剑指Offer64_SumNums {

    public int sumNums(int n) {
        int[] a = new int[n];
        a[0] = n;
        sumNums(n - 1, 1, a);
        return a[n - 1];
    }

    public void sumNums(int n, int pos, int[] arr) {
        try {
            arr[pos] = arr[pos - 1] + n;
            sumNums(n - 1, pos + 1, arr);
        } catch (Exception e) {
        }
    }

}
