import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainTest {
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
//        long t1 = timecountLoop(() -> {
//            String[] split = s.split(" ");
//            int I = Integer.parseInt(split[0]);
//            String S = split[1];
//        }, 10000000);
//
//        long t2 = timecountLoop(() -> {
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

//        int d = new P461_HammingDistance().new Solution().hammingDistance(3, 1);
//        System.out.println(d);

//        timecountLoop(() -> {
//            int r = 512;
//            while (r > 0) {
//                if (flag) {
//                    System.out.print(r + ", ");
//                }
//                r /= 2;
//            }
//            flag = false;
//        }, 100000000);
//
//        flag = true;
//        timecountLoop(() -> {
//            int r = 512;
//            for (int i = 1; r > 0; i++) {
//                if (flag) {
//                    System.out.print(r + ", ");
//                }
//                r = 512 >> i;
//            }
//            flag = false;
//        }, 100000000);
//        flag = true;
//        timecountLoop(() -> {
//            int r = 512;
//            while (r > 0) {
//                if (flag) {
//                    System.out.print(r + ", ");
//                }
//                r >>= 1;
//            }
//            flag = false;
//        }, 100000000);
//
//
//        timecountLoop(() -> {}, 100000000);
//        timecountLoop(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }, 100000000);

//        System.out.println(Integer.MAX_VALUE);
//        List<Integer> randomList = randomIntegerList(1000000);
//
//        timecount(() -> {
//            max = Integer.MIN_VALUE;
//            for (int i = 0; i < randomList.size(); i++) {
//                int aInt = randomList.get(i);
//                if (aInt > max) {
//                    max = aInt;
//                }
//            }
//            System.out.println("max = " + max);
//        });
//        timecount(() -> {
//            max = Integer.MIN_VALUE;
//            for (int i : randomList) {
//                if (i > max) {
//                    max = i;
//                }
//            }
//            System.out.println("max = " + max);
//        });
//        timecount(() -> {
//            max = Integer.MIN_VALUE;
//            Iterator<Integer> it = randomList.iterator();
//            while (it.hasNext()) {
//                int aInt = it.next();
//                if (aInt > max) {
//                    max = aInt;
//                }
//            }
//            System.out.println("max = " + max);
//        });
//        String ops[] = {"5", "2", "2", "2", "2", "2", "2", "2", "2", "C", "D", "C", "C", "C", "C", "C", "C"};
//        int sum = new P682_BaseballGame().new Solution().calPoints(ops);
//        System.out.println(sum);
        int[] source = {1, 2};
        timecountLoop(() -> {
            int[] copy = Arrays.copyOf(source, 2);
        }, 100000000);
        timecountLoop(() -> {
            int[] copy = new int[2];
            copy[0] = source[0];
            copy[1] = source[1];
        }, 100000000);
    }

    private static int max;

    static boolean flag = true;

    private static long timecount(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        long use = end - start;
        System.out.println("use " + use + "ms");
        return use;
    }

    private static long timecountLoop(Runnable runnable, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
        long end = System.currentTimeMillis();
        long use = end - start;
        System.out.println("use " + use + "ms");
        return use;
    }

    private static List<Integer> randomIntegerList(int size) {
        List<Integer> result = new ArrayList<>(size);

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            result.add(random.nextInt());
        }
        return result;
    }
}
