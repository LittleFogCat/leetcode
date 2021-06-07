package top.littlefogcat.leetcode.utils;

public class ReflectUtil {
    public static Class unbox(Class boxed) {
        if (boxed == Integer.class) return int.class;
        if (boxed == Long.class) return long.class;
        // ……
        return boxed;
    }
}
