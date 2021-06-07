package top.littlefogcat.leetcode;

public class P824_GoatLatin {
    class Solution {
        public String toGoatLatin(String S) {
            String[] strs = S.split(" ");
            StringBuilder result = new StringBuilder();
            StringBuilder A = new StringBuilder("a");
            for (String str : strs) {
                StringBuilder aString = new StringBuilder(str);
                char aChar = aString.charAt(0);
                if (!isVowel(aChar)) {
                    aString.deleteCharAt(0).append(aChar);
                }
                aString.append("ma");
                aString.append(A);
                A.append('a');

                result.append(aString).append(' ');
            }
            return result.substring(0, result.length() - 1);
        }

        private boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                    c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
        }
    }
}
