package top.littlefogcat.leetcode;

public class P791_CustomSortString {
    class Solution {
        public String customSortString(String S, String T) {
            int flag = 0;
            char[] chars = T.toCharArray();
            for (char c : S.toCharArray()) {
                // these two while loops both work
//                int ind;
//                while (flag < (ind = lastIndexOf(chars, c))) {
//                    swap(chars, flag, ind);
//                    System.out.println(chars);
//                    flag++;
//                }
//                if (ind == flag) flag++;
                while (true) {
                    int ind = lastIndexOf(chars, c);
                    if (ind > flag) {
                        swap(chars, flag, ind);
                        flag++;
                    } else if (ind == flag) {
                        flag++;
                        break;
                    } else {
                        break;
                    }
                }
            }

            return new String(chars);
        }

        private int lastIndexOf(char[] chars, char c) {
            for (int i = chars.length - 1; i >= 0; i--) {
                if (chars[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private void swap(char[] arr, int i, int j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
