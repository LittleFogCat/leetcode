package top.littlefogcat.leetcode;

import java.util.*;

public class P49_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int key = hash(s);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(s);
        }
        return new ArrayList<>(map.values());
    }

    private int hash(String s) {
        int[] counts = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            counts[ch - 'a']++;
        }
        return Arrays.hashCode(counts);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.hashCode(new int[]{3, 2, 4}));
        System.out.println(Arrays.hashCode(new int[]{3, 2, 4}));
    }

}
