package top.littlefogcat.leetcode;

import top.littlefogcat.leetcode.utils.Util;

import java.util.*;

public class P5638_MaximumNumberOfEatenApples {
    public int eatenApples1(int[] apples, int[] days) {
        List<Apple> appleList = new ArrayList<>(); // 苹果批次的列表，以过期日期倒序
        int ate = 0, appleCount = 0;
        for (int i = 0; i < apples.length || appleCount > 0; i++) {
//            System.out.print("第" + i + "天：");
            if (i < apples.length && days[i] > 0 && apples[i] > 0) { // 生产新的
                int expire = i + days[i];
                // 二分法插入当前批次
                int l = 0, h = appleList.size() - 1, m = 0;
                while (l <= h) {
                    m = (l + h) >> 1;
                    Apple current = appleList.get(m);
                    // 插入到当前位置
                    if (current.expire == expire ||
                            current.expire < expire && (m == 0 || appleList.get(m - 1).expire > expire) ||
                            m == appleList.size() - 1 && expire < current.expire && m++ > 0
                    ) break;
                    // 查询左边
                    if (current.expire < expire) {
                        h = m - 1;
                    } else { // 查询右边
                        l = m + 1;
                    }
                }
                appleList.add(m, new Apple(expire, apples[i]));
//                insert(appleList, new Apple(expire, apples[i]));
                appleCount += apples[i];
//                System.out.print("生产了" + apples[i] + "个，");
            }
            // 丢弃当天过期的
            int lastIndex;
            while (appleList.size() > 0 && appleList.get(lastIndex = appleList.size() - 1).expire == i) {
                appleCount -= appleList.get(lastIndex).count;
//                System.out.print("丢弃了" + appleList.get(lastIndex).count + "个，");
                appleList.remove(lastIndex);
            }
            if (appleCount > 0) { // 吃一个最早过期的
                lastIndex = appleList.size() - 1;
                Apple last = appleList.get(lastIndex);
                last.count--;
                if (last.count == 0) appleList.remove(lastIndex);
                ate++;
                appleCount--;
//                System.out.print("吃了1个，");
            }
//            System.out.println("剩余" + appleCount + "个");
        }
        return ate;
    }

    private static void insert(List<Apple> list, Apple apple) { // 二分法按顺序插入
        int l = 0, h = list.size() - 1, m = 0;
        while (l <= h) {
            m = (l + h) >> 1;
            Apple current = list.get(m);
            // 插入到当前位置
            if (current.expire == apple.expire ||
                    current.expire < apple.expire && (m == 0 || list.get(m - 1).expire > apple.expire) ||
                    m == list.size() - 1 && apple.expire < current.expire && m++ > 0
            ) break;
            // 查询左边
            if (current.expire < apple.expire) {
                h = m - 1;
            } else { // 查询右边
                l = m + 1;
            }
        }
        list.add(m, apple);
    }

    static class Apple { // 一个批次的苹果，对应一个Apple实例
        public Apple(int expire, int count) {
            this.expire = expire;
            this.count = count;
        }

        int expire;
        int count;

        @Override
        public String toString() {
            return String.valueOf(expire);
        }
    }

    public int eatenApples2(int[] apples, int[] days) {
        PriorityQueue<Apple> appleList = new PriorityQueue<>(Comparator.comparingInt(a -> a.expire)); // 苹果批次的列表，以过期日期倒序
        int ate = 0, appleCount = 0;
        for (int i = 0; i < apples.length || appleCount > 0; i++) {
            if (i < apples.length && days[i] > 0 && apples[i] > 0) { // 生产新的
                int expire = i + days[i];
                // 新的苹果添加到当前堆中
                appleList.offer(new Apple(expire, apples[i]));
                appleCount += apples[i];
            }
            // 丢弃当天过期的
            for (Apple a; (a = appleList.peek()) != null && a.expire == i; ) {
                appleList.poll();
                appleCount -= a.count;
            }
            if (appleCount > 0) { // 吃一个最早过期的
                Apple first = appleList.peek();
                if (first.count == 1) appleList.poll();
                else first.count--;
                appleCount--;
                ate++;
            }
        }
        return ate;
    }

    public int eatenApples(int[] apples, int[] days) {
        // int[0]保存过期时间，int[1]保存个数
        PriorityQueue<int[]> appleList = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // 苹果批次的列表，以过期日期倒序
        int ate = 0, appleCount = 0;
        for (int i = 0; i < apples.length || appleCount > 0; i++) {
            if (i < apples.length && days[i] > 0 && apples[i] > 0) { // 生产新的
                int expire = i + days[i];
                // 新的苹果添加到当前堆中
                appleList.offer(new int[]{expire, apples[i]});
                appleCount += apples[i];
            }
            // 丢弃当天过期的
            for (int[] a; (a = appleList.peek()) != null && a[0] == i; ) {
                appleList.poll();
                appleCount -= a[1];
            }
            if (appleCount > 0) { // 吃一个最早过期的
                int[] first = appleList.peek();
                if (first[1] == 1) appleList.poll();
                else first[1]--;
                appleCount--;
                ate++;
            }
        }
        return ate;
    }

    public static void main(String[] args) {
        P5638_MaximumNumberOfEatenApples p = new P5638_MaximumNumberOfEatenApples();
        Util.assertEqual(7, p.eatenApples(arr(1, 2, 3, 5, 2), arr(3, 2, 1, 4, 2)));
        Util.assertEqual(20000, p.eatenApples(arr(20000), arr(20000)));
        Util.assertEqual(3, p.eatenApples(arr(2, 2), arr(5, 1)));
//        List<Apple> list = new ArrayList<>();
//        list.add(new Apple(9, 1));
//        list.add(new Apple(7, 1));
//        list.add(new Apple(6, 1));
//        list.add(new Apple(4, 1));
//        list.add(new Apple(2, 1));
//        list.add(new Apple(2, 1));
//        P5638_MaximumNumberOfEatenApples.insert(list, new Apple(5, 1));
//        P5638_MaximumNumberOfEatenApples.insert(list, new Apple(7, 1));
//        P5638_MaximumNumberOfEatenApples.insert(list, new Apple(1, 1));
//        P5638_MaximumNumberOfEatenApples.insert(list, new Apple(2, 1));
//        P5638_MaximumNumberOfEatenApples.insert(list, new Apple(12, 1));
//        System.out.println(list);
//        System.out.println(Arrays.asList(12, 9, 7, 7, 6, 5, 4, 2, 2, 2, 1));
    }

    private static int[] arr(int... nums) {
        return Util.arr(nums);
    }
}
