package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1002_FindCommonCharacters {
    public List<String> commonChars(String[] A) {
        int[] rs = new int[26];
        int[] temp = new int[26];
        List<String> result = new ArrayList<>();
        for (int i = 0; i < A[0].length(); i++) {
            temp[A[0].charAt(i) - 'a']++;
        }
        System.arraycopy(temp, 0, rs, 0, 26);
        for (int i = 1; i < A.length; i++) {
            Arrays.fill(temp, 0);
            for (int j = 0; j < A[i].length(); j++) temp[A[i].charAt(j) - 'a']++;
            for (int j = 0; j < 26; j++) if (temp[j] < rs[j]) rs[j] = temp[j];
        }
        for (int i = 0; i < rs.length; i++) {
            char c = (char) ('a' + i);
            for (int j = 0; j < rs[i]; j++) result.add(String.valueOf(c));
        }
        return result;
    }

}
