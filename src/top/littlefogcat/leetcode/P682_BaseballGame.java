package top.littlefogcat.leetcode;

import java.util.Stack;

public class P682_BaseballGame {
    class Solution {
        public int calPoints(String[] ops) {
            Stack<Integer> points = new Stack<>();
            for (String op : ops) {
                switch (op) {
                    case "C":
                        points.pop();
                        break;
                    case "D":
                        points.push(points.peek() * 2);
                        break;
                    case "+":
                        int last = points.pop();
                        int last2 = points.peek();
                        points.push(last);
                        points.push(last + last2);
                        break;
                    default:
                        points.push(Integer.parseInt(op));
                        break;
                }
            }
            int sum = 0;
            for (Integer point : points) {
                sum += point;
            }
            return sum;
        }
    }
}
