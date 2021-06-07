package top.littlefogcat.leetcode;

import java.util.*;

public class P649_Dota2Senate {

    public String predictPartyVictory1(String senate) {
        char[] chars = senate.toCharArray();
        while (true) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'x') continue; // 跳过
                if (chars[i] == 'R') {
                    int indexOfD = indexOf(chars, 'D', i + 1);
                    if (indexOfD == -1) return "Radiant";
                    chars[indexOfD] = 'x';
                } else {
                    int indexOfR = indexOf(chars, 'R', i + 1);
                    if (indexOfR == -1) return "Dire";
                    chars[indexOfR] = 'x';
                }
            }
        }
    }


    public String predictPartyVictory2(String senate) {
        char[] chars = senate.toCharArray();
        int r = chars[0] == 'R' ? 1 : 0, d = 1 - r;
        ListNode head = new ListNode(chars[0]);
        ListNode p = head;
        for (int i = 1; i < chars.length; i++) {
            p.next = new ListNode(chars[i]);
            p = p.next;
            if (chars[i] == 'R') r++;
            else d++;
        }
        p.next = head;
        p = head;
        while (true) {
            if (r == 0) return "Dire";
            if (d == 0) return "Radiant";
            if (p.val == 'R') { // delete next D
                ListNode nextD = p;
                while (nextD.next.val != 'D') {
                    nextD = nextD.next;
                } // delete nextD.next
                nextD.next = nextD.next.next;
                d--;
            } else { // delete next R
                ListNode nextR = p;
                while (nextR.next.val != 'R') {
                    nextR = nextR.next;
                } // delete nextR.next
                nextR.next = nextR.next.next;
                r--;
            }
            p = p.next;
        }
    }

    public String predictPartyVictory3(String senate) {
        char[] chars = senate.toCharArray();
        LinkedList<Integer> radiants = new LinkedList<>();
        LinkedList<Integer> dires = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R') radiants.offer(i);
            else dires.offer(i);
        }
        int n = senate.length();
        while (true) {
            if (dires.size() == 0) return "Radiant";
            int d = dires.poll();
            if (radiants.size() == 0) return "Dire";
            int r = radiants.poll();
            if (d < r) { // ban掉r
                dires.offer(d + n);
            } else {
                radiants.offer(r + n);
            }
        }
    }

    public String predictPartyVictory(String senate) {
        System.out.println(senate);
        if (senate.length() == 1) return "R".equals(senate) ? "Radiant" : "Dire";
        char[] chars = senate.toCharArray();
        int len = chars.length;
        int size = 0;
        int d = 0;
        char[] next = new char[chars.length];
        boolean hasR = false, hasD = false;
        for (int i = 0; i < len; i++) {
            if (chars[i] == 'D') {
                d++;
                if (d > 0) next[size++] = 'D';
                hasD = true;
            } else {
                d--;
                if (d < 0) next[size++] = 'R';
                hasR = true;
            }
        }
        if (!hasR) return "Dire";
        if (!hasD) return "Radiant";
        System.out.println(new String(next));
        char[] next1 = Arrays.copyOf(next, size);
        return predictPartyVictory(new String(next1));
    }

    private int indexOf(char[] arr, char c, int fromIndex) {
        for (int i = fromIndex; i < arr.length; i++) {
            if (arr[i] == c) return i;
        }
        for (int i = 0; i < fromIndex; i++) {
            if (arr[i] == c) return i;
        }
        return -1;
    }
}
