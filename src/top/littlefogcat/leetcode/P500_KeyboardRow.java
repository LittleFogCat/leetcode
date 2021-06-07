package top.littlefogcat.leetcode;

import java.util.*;

public class P500_KeyboardRow {
    class Solution {
        private String row1 = "qwertyuiopQWERTYUIOP";
        private String row2 = "asdfghjklASDFGHJKL";
        private String row3 = "zxcvbnmZXCVBNM";

        private Map<Character, Integer> rowMap = new HashMap<>();

        {
            for (char c : row1.toCharArray()) {
                rowMap.put(c, 1);
            }
            for (char c : row2.toCharArray()) {
                rowMap.put(c, 2);
            }
            for (char c : row3.toCharArray()) {
                rowMap.put(c, 3);
            }
        }

        public String[] findWords(String[] words) {
            List<String> meetStringList = new ArrayList<>();
            for (String word : words) {
                if (fits(word)) {
                    meetStringList.add(word);
                }
            }
            return meetStringList.toArray(new String[]{});
        }

        private boolean fits(String word) {
            int len = word.length();
            if (len == 1) return true;
            int row = rowMap.get(word.charAt(0));
            for (int i = 1; i < len; i++) {
                if (rowMap.get(word.toCharArray()[i]) != row) {
                    return false;
                }
            }
            return true;
        }
    }
}
