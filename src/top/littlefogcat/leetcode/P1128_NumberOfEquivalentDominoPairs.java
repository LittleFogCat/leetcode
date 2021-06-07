package top.littlefogcat.leetcode;

public class P1128_NumberOfEquivalentDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] numsCount = new int[128];
        for (int[] d : dominoes) {
            int num = d[0] > d[1] ? d[0] << 4 | d[1] : d[1] << 4 | d[0];
            numsCount[num]++;
        }
        int ans = 0;
        for (int cnt : numsCount) {
            if (cnt < 2) continue;
            ans += cnt * (cnt - 1) / 2;
        }
        return ans;
    }
}
