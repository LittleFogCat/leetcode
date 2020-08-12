package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P28_ImplementStrStr {
    public int strStr(String s, String p) {
        if (p.length() == 0) return 0;
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        int size = ss.length - pp.length;
        for (int i = 0; i <= size; i++) {
            boolean match = true;
            for (int j = 0; j < pp.length; j++) {
                if (ss[i + j] != pp[j]) {
                    match = false;
                    break;
                }
            }
            if (match) return i;
        }
        return -1;
    }

    int MOD = 82595524;
    int[] mods; // mods[i] = 2^i & MOD

    public int strStrWithHash(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length()) return -1;
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        mods = new int[n.length];
        mods[0] = 1;
        for (int i = 1; i < mods.length; i++) {
            mods[i] = mods[i - 1] * 26 % MOD;
        }
        System.out.println("mods: " + Arrays.toString(mods));
        int hashN = 0;
        int hashH = 0;
        int size = h.length - n.length;
        for (int i = 0; i <= size; i++) {
            if (i == 0) {
                for (int pos = 0; pos < n.length; pos++) {
                    hashN = (hashN + c2i(n[pos]) * mods[pos] % MOD) % MOD;
                    hashH = (hashH + c2i(h[pos]) * mods[pos] % MOD) % MOD;
                }
            } else {
                int delete = c2i(h[i - 1]);
                int add = c2i(h[i + n.length - 1]) * mods[mods.length - 1];
                hashH = (hashH - delete) / 26 + add;
                System.out.println("delete: " + delete + ", add: " + add);
                hashH = hashH % MOD;
            }
            System.out.println("i = " + i + ":");
            System.out.println("hashH: " + hashH + ", hashN: " + hashN);
            System.out.println("hashH: " + hash(h, i, n.length) + ", hashN: " + hashN);
            if (hashN == hashH) {
                System.out.println("hashN == hashH == " + hashH);
                // 防止哈希碰撞
                boolean match = true;
                for (int j = 0; j < n.length; j++) {
                    if (h[i + j] != n[j]) {
                        match = false;
                        break;
                    }
                }
                if (match) return i;
            }
        }
        return -1;
    }

    private int c2i(char c) {
        return c - 'a';
    }

    private int[] mods(int n) {
        if (mods != null) return mods;
        mods = new int[n];
        mods[0] = 1;
        for (int i = 1; i < mods.length; i++) {
            mods[i] = mods[i - 1] * 26 % MOD;
        }
        return mods;
    }

    private int hash(char[] arr, int first, int len) {
        int hash = 0;
        for (int i = first; i < first + len; i++) {
            int add = c2i(arr[i]) * mods(len)[i - first];
            hash += add % MOD;
            hash %= MOD;
        }
        return hash;
    }

    private int hash(char[] arr) {
        return hash(arr, 0, arr.length);
    }

    public static void main(String[] args) {
        P28_ImplementStrStr p = new P28_ImplementStrStr();
        System.out.println(p.strStrWithHash("aabaaabaaac", "aabaaac"));
//        System.out.println(p.strStrWithHash("aabaaabaaac", "ba"));

//        System.out.println(p.hash("abc".toCharArray()));
    }

}
