package top.littlefogcat.leetcode;

import java.util.HashSet;
import java.util.Set;

public class P804_UniqueMorseCodeWords {


    class Solution {
        public void printMorse() {
            for (int i = 0; i < MORSES.length; i++) {
                char c = (char) (i + 'a');
                System.out.println(c + ": " + MORSES[i]);
            }
        }

        private String[] MORSES = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        private Set<String> mMorseSet = new HashSet<>();

        public int uniqueMorseRepresentations(String[] words) {
            for (String word : words) {
                mMorseSet.add(str2morse(word));
            }
            return mMorseSet.size();
        }

        private String str2morse(String origin) {
            StringBuilder ret = new StringBuilder();

            for (char c : origin.toCharArray()) {
                ret.append(ch2morse(c));
            }
            return ret.toString();
        }

        private String ch2morse(char c) {
            return MORSES[c - 'a'];
        }
    }
}
