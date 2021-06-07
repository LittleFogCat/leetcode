package top.littlefogcat.leetcode;

public class P1684_CountTheNumberOfConsistentStrings {
    public int countConsistentStrings(String allowed, String[] words) {
        int[] allow = new int[128];
        for (int i = 0; i < allowed.length(); i++) {
            allow[allowed.charAt(i)] = 1;
        }
        int count = 0;
        out:
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (allow[word.charAt(i)] == 0) continue out;
            }
            count++;
        }
        return count;
    }
}
