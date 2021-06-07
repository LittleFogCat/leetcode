package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class P93_RestoreIPAddresses {
    public static void main(String[] args) {
        P93_RestoreIPAddresses p = new P93_RestoreIPAddresses();
        System.out.println(p.restoreIpAddresses("25525511135"));
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> r = new ArrayList<>();
        restoreIpAddresses(s, 0, new LinkedList<>(), r);
        return r;
    }

    private void restoreIpAddresses(String s, int start, LinkedList<String> current, List<String> result) {
        if (current.size() == 4) {
//            System.out.println(current);
            if (start != s.length()) {
                return;
            }
            StringBuilder tmp = new StringBuilder(current.get(0));
            for (int i = 1; i < current.size(); i++) {
                tmp.append(".").append(current.get(i));
            }
            result.add(tmp.toString());
            return;
        }
        // i: 当前项长度，ip地址的每一项长度是1到3位。
        for (int i = 1; i <= 3; i++) {
            if (i != 1 && s.charAt(start) == '0') {
                break;
            }
            int next = start + i;
            if (next > s.length()) break;
            String currentItem = s.substring(start, next);
//            System.out.println(currentItem);
            int currentItemInt = Integer.parseInt(currentItem);
            if (currentItemInt > 255 || !checkValid(s, next, current)) {
                continue;
            }
            current.add(currentItem);
//            System.out.println(current);
            restoreIpAddresses(s, next, current, result);
            current.removeLast();
        }
    }

    /**
     * 检查当前情况下是否可能有解。
     * 因为ip地址的每一项是1到3位，所以剩下x项的话，需要长度为x到3x
     *
     * @return 当前情况下是否可能有解
     */
    private boolean checkValid(String s, int start, LinkedList<String> current) {
        int remainStringLength = s.length() - start;
        int remainItemSize = 3 - current.size();
        return remainStringLength >= remainItemSize && remainStringLength <= 3 * remainItemSize;
    }


}
