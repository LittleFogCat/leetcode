package top.littlefogcat.leetcode;

public class P38_CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        else {
            char[] last = countAndSay(n - 1).toCharArray();
            StringBuilder s = new StringBuilder();
            int count = 1;
            for (int i = 1; i < last.length; i++) {
                char ch = last[i];
                if (ch != last[i - 1]) {
                    s.append(count).append(last[i - 1]);
                    count = 1;
                } else count++;
            }
            s.append(count).append(last[last.length - 1]);
            return s.toString();
        }
    }

}
