package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P929_UniqueEmailAddresses {
    class Solution {
        public int numUniqueEmails(String[] emails) {
            List<String> emailList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (String email : emails) {
                email = simple2(email, sb);
                System.out.println(email);
                if (!emailList.contains(email)) {
                    emailList.add(email);
                }
            }

            return emailList.size();
        }

        public String simple(String email) {
            int index1 = email.indexOf("+");
            int index2 = email.indexOf("@");
            String s1 = email.substring(0, index1 == -1 ? index2 : index1)
                    .replace(".", "");
            String s2 = email.substring(index2);

            return s1 + s2;
        }

        public String simple1(String email) {
            StringBuilder sb = new StringBuilder();
            int len = email.length();
            byte flag = 0;
            for (int i = 0; i < len; i++) {
                char c = email.charAt(i);
                if (c == '+') flag++;
                if (c == '@') flag = -1;
                if (flag == -1 || flag == 0 && c != '.') sb.append(c);
            }
            return sb.toString();
        }

        public String simple2(String email, StringBuilder sb) {
            sb.setLength(0);
            int len = email.length();
            int index = 0;
            boolean flag = true;
            for (; ; index++) {
                char c = email.charAt(index);
                if (c == '@') break;
                if (c == '+') flag = false;
                if (flag && c != '.') sb.append(c);
            }
            sb.append(email, index, len);
            return sb.toString();
        }
    }
}
