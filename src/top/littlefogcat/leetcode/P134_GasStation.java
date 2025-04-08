package top.littlefogcat.leetcode;

/**
 * 思路：
 * 一个点可以完成旅途的条件：
 * 从这个点到终点途中剩余汽油 >= 0
 * 从这个点到终点积攒的汽油 >= 从0点到这个点途中花费最多的汽油
 * <p>
 * 可以简单推断出，只要汽油积攒消耗的总和大于等于0，则必有解，反之则无解。
 * 由题目可知，如果有解则必只有一个解，故这个唯一解必然是最大下降序列之后的第一个点。
 */
public class P134_GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        final int len = gas.length;
        int sum = 0;
        int minSum = gas[0] - cost[0];
        int minIndex = 0; // 最大下降序列的终点
        for (int i = 0; i < len; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum) {
                minSum = sum;
                minIndex = i;
            }
        }
        if (sum < 0) return -1; // 无解
        return (minIndex + 1) % len;
    }

}
