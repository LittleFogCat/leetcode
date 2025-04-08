package top.littlefogcat.leetcode;

import java.util.*;

public class P368_LargestDivisibleSubset {
    Map<Integer, Integer> countMap = new HashMap<>();
    Map<Integer, Integer> nexts = new HashMap<>();

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        // 第一遍先计算数量
        var start = 0;
        var maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (countMap.containsKey(i)) {
                continue;
            }
            var len = getLargestCount(nums, i);
            if (len >= maxLen) {
                maxLen = len;
                start = i;
            }
        }

        var result = new ArrayList<Integer>();
        var p = start;
        while (true) {
            result.add(nums[p]);
            if (!nexts.containsKey(p)) break;
            p = nexts.get(p);
        }
        return result;
    }

    int getLargestCount(int[] nums, int baseIndex) {
        if (countMap.get(baseIndex) != null) {
            return countMap.get(baseIndex);
        }
        var max = 0;
        var next = -1;
        for (int i = baseIndex + 1; i < nums.length; i++) {
            if (nums[i] % nums[baseIndex] == 0) {
                var iMax = getLargestCount(nums, i);
                if (iMax > max) {
                    max = iMax;
                    next = i;
                }
            }
        }
        if (next != -1) {
            nexts.put(baseIndex, next);
        }
        var r = max + 1;
        countMap.put(baseIndex, r);
        return r;
    }
}

class P368_LargestDivisibleSubset1 {

    int[] counts;
    int[] nexts;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        counts = new int[nums.length];
        nexts = new int[nums.length];
        // 第一遍先计算数量
        var start = 0;
        var maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (counts[i] != 0) {
                continue;
            }
            var len = getLargestCount(nums, i);
            if (len >= maxLen) {
                maxLen = len;
                start = i;
            }
        }

        var result = new ArrayList<Integer>();
        var p = start;
        while (true) {
            result.add(nums[p]);
            if (nexts[p] == 0) break;
            p = nexts[p];
        }
        return result;
    }

    int getLargestCount(int[] nums, int baseIndex) {
        if (counts[baseIndex] != 0) {
            return counts[baseIndex];
        }
        var max = 0;
        var next = -1;
        for (int i = baseIndex + 1; i < nums.length; i++) {
            if (nums[i] < nums[baseIndex] * 2) continue;
            if (nums[i] % nums[baseIndex] == 0) {
                var iMax = getLargestCount(nums, i);
                if (iMax > max) {
                    max = iMax;
                    next = i;
                }
            }
        }
        if (next != -1) {
            nexts[baseIndex] = next;
        }
        var r = max + 1;
        counts[baseIndex] = r;
        return r;
    }
}

class P368_LargestDivisibleSubset2 {
    public List<Integer> largestDivisibleSubset(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] dp = new int[n];
        int[] ind = new int[n];
        int res = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ind[i] = i;
            dp[i] = 1;
            int limit = (arr[i] + 1) / 2;
            for (int j = 0; j < i && arr[j] <= limit; j++) {
                if (arr[i] % arr[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = 1 + dp[j];
                    ind[i] = j;
                }
            }
            res = dp[i] > dp[res] ? i : res;
        }
        while (res != ind[res]) {
            ans.add(arr[res]);
            res = ind[res];
        }
        ans.add(arr[res]);
        Collections.reverse(ans);
        return ans;
    }

}
