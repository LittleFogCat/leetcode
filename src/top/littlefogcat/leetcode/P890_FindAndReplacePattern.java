package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P890_FindAndReplacePattern {
    public static void main(String[] args) {
        Solution solution = new P890_FindAndReplacePattern().new Solution();
        String[] words = {"mee", "ccc", "aac", "acc"};
        List<String> res = solution.findAndReplacePattern(words, "abb");
        System.out.println(res);
    }

    class Solution {
        private Map<Character, Integer> map = new HashMap<>();

        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> result = new ArrayList<>();
            for (String word : words) {
                if (check(word, pattern)) {
                    result.add(word);
                }
            }
            return result;
        }

        private boolean check(String word, String pattern) {
            return check(word, str2IntArr(pattern));
        }

        private int[] str2IntArr(String s) {
            map.clear();
            int[] res = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!map.containsKey(c)) {
                    map.put(c, i);
                    res[i] = i;
                } else {
                    int cIndex = map.get(c);
                    res[i] = cIndex;
                }
            }
            return res;
        }

        private boolean check(String s, int[] base) {
            map.clear();
            char[] sCharArr = s.toCharArray();
            for (int i = 0; i < base.length; i++) {
                int baseI = base[i];
                char c = sCharArr[i];
                if (baseI == i) {
                    // indicate that this is a new one.
                    if (map.containsKey(c)) {
                        return false;
                    }
                    map.put(c, i);
                } else {
                    // this char has already shown
                    if (!map.containsKey(c)) {
                        return false;
                    }
                    int cIndex = map.get(c);
                    if (cIndex != baseI) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
