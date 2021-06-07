package top.littlefogcat.leetcode;

public class P1370_IncreasingDecreasingString {
    public String sortString(String s) {
        int[] counts = new int[129];
        char[] s2c = s.toCharArray();
        for (char c : s2c) {
            counts[c]++;
        }
        StringBuilder sb = new StringBuilder();
        int min = 'a', max = 'z';
        while (sb.length() != s2c.length) {
            // 顺序
            if (counts[min] == 0)
                for (int i = min + 1; i < max; i++) {
                    if (counts[i] != 0) {
                        min = i;
                        break;
                    }
                }
            for (int i = min; i <= max; i++) {
                if (counts[i] != 0) {
                    char c = (char) (i);
                    sb.append(c);
                    counts[i]--;
                }
            }
            // 逆序
            if (counts[max] == 0)
                for (int i = max - 1; i >= min; i--) {
                    if (counts[i] != 0) {
                        max = i;
                        break;
                    }
                }
            for (int i = max; i >= min; i--) {
                if (counts[i] != 0) {
                    char c = (char) (i);
                    sb.append(c);
                    counts[i]--;
                }
            }
        }
        return sb.toString();
    }
}
