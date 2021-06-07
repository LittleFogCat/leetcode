package top.littlefogcat.leetcode;

public class P1409_QueriesOnAPermutationWithKey {
    public int[] processQueries1(int[] queries, int m) {
        int[] P = new int[m];
        for (int i = 0; i < m; i++) P[i] = i + 1;
        for (int i = 0; i < queries.length; i++) {
            // 模拟查询过程
            for (int j = 0; j < P.length; j++) {
                if (P[j] == queries[i]) {
                    System.arraycopy(P, 0, P, 1, j);
                    P[0] = queries[i];
                    queries[i] = j;
                    break;
                }
            }
        }
        return queries;
    }

    public int[] processQueries(int[] queries, int m) {
        for (int i = 0; i < queries.length; i++) queries[i]--; // 将queries转化为对应的位置
        for (int i = 0; i < queries.length; i++) {
            for (int j = i + 1; j < queries.length; j++) {
                if (queries[j] < queries[i]) // 在queries[i]前方的往后延一位
                    queries[j]++;
                else if (queries[j] == queries[i]) // 查询到了，移动到最前方
                    queries[j] = 0;
            }
        }
        return queries;
    }

    // todo 树状数组
}
