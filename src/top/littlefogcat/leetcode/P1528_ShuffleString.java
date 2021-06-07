package top.littlefogcat.leetcode;

public class P1528_ShuffleString {
    public String restoreString0(String s, int[] indices) {
        char[] shuffle = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            shuffle[indices[i]] = s.charAt(i);
        }
        return new String(shuffle);
    }

}
