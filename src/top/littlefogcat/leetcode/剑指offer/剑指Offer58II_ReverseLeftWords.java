package top.littlefogcat.leetcode.剑指offer;

public class 剑指Offer58II_ReverseLeftWords {
    public String reverseLeftWords(String s, int n) {
        n = n % s.length();
        return s.substring(n) + s.substring(0, n);
    }
}
