package top.littlefogcat.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

import static top.littlefogcat.leetcode.Util.swap;

public class P0807_PermutationI {
    int p = 0;

    public String[] permutation(String S) {
        int len = 1;
        for (int i = 2; i <= S.length(); i++) {
            len *= i;
        }
        String[] results = new String[len];
        helper(S.toCharArray(), S.length(), new char[S.length()], 0, results);
        return results;
    }

    private void helper(char[] unused, int unusedLen, char[] current, int len, String[] results) {
        if (unusedLen == 0) {
            results[p++] = new String(current);
        } else for (int i = 0; i < unusedLen; i++) {
            current[len] = unused[i];
            swap(unused, i, unusedLen - 1);
            helper(unused, unusedLen - 1, current, len + 1, results);
            swap(unused, i, unusedLen - 1); // 回溯
            current[len] = 0;
        }
    }

    private void swap(char[] arr, int p, int q) {
        char t = arr[p];
        arr[p] = arr[q];
        arr[q] = t;
    }
}
