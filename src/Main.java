import extra.E01_FullPermutation;

import java.util.*;

public class Main {
    public static void main(String args[]) {
//        int[][] ts = {{3, 0, 8, 4},
//                {2, 4, 5, 7},
//                {9, 2, 6, 3},
//                {0, 3, 1, 0}};
//
//        System.out.println(new P807_MaxIncreasetoKeepCitySkyline().new Solution().maxIncreaseKeepingSkyline(ts));
//
//        int[][] ts2 = {
//                {57, 86, 30, 56},
//                {59, 60, 83, 95},
//                {83, 37, 38, 98},
//                {15, 39, 56, 86}
//        };
//        System.out.println(new P807_MaxIncreasetoKeepCitySkyline().new Solution().maxIncreaseKeepingSkyline(ts2));
//
//        long start = System.currentTimeMillis();
//        Set<String> allp = new AllP().allP("12ehreg34ab9");
//        float use = System.currentTimeMillis() - start;
//        use /= 1000;
//        System.out.println(use + " allp = " + allp.size());
//        long start = System.currentTimeMillis();
//        Set<String> set = new E01_FullPermutation().new Solution("123456789").allP();
//        System.out.println(set.size());
//        System.out.println("use " + (System.currentTimeMillis() - start) + "ms");

//        new P804_UniqueMorseCodeWords().printMorse();

//        int[][] graph = {{1, 2}, {3}, {3}, {}};
//        System.out.println(new P797_AllPathsFromSourcetoTarget().new Solution().allPathsSourceTarget(graph));

//        Map<String, Integer> map = new HashMap<>();
//        System.out.println(map.get("1"));

//        String s = "123 jifewofji";
//        long t1 = timecountIterable(() -> {
//            String[] split = s.split(" ");
//            int I = Integer.parseInt(split[0]);
//            String S = split[1];
//        }, 10000000);
//
//        long t2 = timecountIterable(() -> {
//            int index = s.indexOf(" ");
//            int I = Integer.parseInt(s.substring(0, index));
//            String S = s.substring(index + 1);
//        }, 10000000);
//
//        System.out.println(t1 + ", " + t2);

//        System.out.println((~5));
//        System.out.println(Integer.toBinaryString(5));
//        System.out.println(Integer.toBinaryString(-6));
//        System.out.println(~5 & 7);
//        System.out.println(~5 & (1 << 3) - 1);

//        System.out.println("1c2adb3".indexOf('c', -1));
//        System.out.println(new P557_ReverseWordsInAStringIII().new Solution().reverseWords("hello world"));
//        Set<String> stringSet = Collections.emptySet();
//        stringSet.add("hje");
//        System.out.println(stringSet);
//        char[] chars = {'a', 'b'};
//        System.out.println(Arrays.asList(chars));

//        String s = "asdb";
//        s.toCharArray()[0] = 3;
//        System.out.println(s);

//        String S = "kqep";
//        String T = "pekeq";
//        System.out.println("S: " + S + ", T: " + T);
//        String res = new P791_CustomSortString().new Solution().customSortString(S, T);
//        System.out.println("Result: " + res);

        int d = new P461_HammingDistance().new Solution().hammingDistance(3, 1);
        System.out.println(d);
    }

    private static long timecount(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long timecountIterable(Runnable runnable, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
