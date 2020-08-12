package top.littlefogcat.leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * <p>
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 * <p>
 * 示例 1:
 * <p>
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出: [20,24]
 * 解释:
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 注意:
 * <p>
 * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
 * 1 <= k <= 3500
 * -10^5 <= 元素的值 <= 10^5
 * 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
 */
public class P632_SmallestRangeCoveringElementsFromKLists {
    private void checkRange(int small, int large) {
        if (large - small < smallestRange[1] - smallestRange[0] ||
                large - small == smallestRange[1] - smallestRange[0] && small < smallestRange[0]) {
            smallestRange[0] = small;
            smallestRange[1] = large;
        }
    }

    Integer[] values;
    int[][] dp;

    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null) return null;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<Integer> valueSet = new HashSet<>();
        for (List<Integer> integers : nums) {
            for (Integer integer : integers) {
                valueSet.add(integer);
                if (integer > max) max = integer;
                if (integer < min) min = integer;
            }
        }
        values = valueSet.toArray(new Integer[]{});
        Arrays.sort(values);
        // System.out.println(Arrays.toString(values));
        if (min == Integer.MAX_VALUE) return null;
        smallestRange = new int[]{values[0], values[values.length - 1]};
        dp = new int[values.length][values.length];
        smallestRange(nums, 0, values.length - 1);
        return smallestRange;
    }

    private int[] smallestRange;

    private void smallestRange(List<List<Integer>> nums, int smallPos, int largePos) {
        if (smallPos > largePos) return; // 防止意外
        if (dp[smallPos][largePos] != 0) return;
        dp[smallPos][largePos] = 1;
        for (List<Integer> arr : nums) {
            if (!isInRange(arr, values[smallPos], values[largePos])) return;
        }
//        System.out.println("range(" + values[smallPos] + ", " + values[largePos] + ") is valid.");
        // all in range
        checkRange(values[smallPos], values[largePos]);
        if (smallPos != largePos) {
//            int mid = (small + large) / 2;
            smallestRange(nums, smallPos, largePos - 1);
            smallestRange(nums, smallPos + 1, largePos);
        }
    }

    /**
     * 判断一个数组是否有元素在区间内
     */
    private boolean isInRange(List<Integer> arr, int small, int large) {
        if (arr == null || arr.size() == 0) return false;
        // 使用二分法判断数组是否有元素在区间内
        int left = 0;
        int right = arr.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = arr.get(mid);
            if (midVal >= small && midVal <= large) {
//                System.out.println("arr" + arr + " is in range(" + small + ", " + large + ")");
                return true;
            } else if (midVal < small) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
//        System.out.println("arr" + arr + " not in range(" + small + ", " + large + ")");
        return false;
    }

    // ---------------------------------------------------

    public int[] smallestRange2(List<List<Integer>> nums) {
        int[] smallestRange = new int[]{-100000, 100000};
        int[] pos = new int[nums.size()];
        for (; ; ) {
            // 每次从循环中取nums[pos[i]]
            // 每一个循环中，需要干2件事
            // 1. 更新当前范围
            // 2. 更新pos数组
            int minPos = 0, maxPos = 0; // 如果当前批次最小的那个数已经是所属数组最大值了，那么就不用继续循环了。
            for (int i = 0; i < pos.length; i++) {
                int num = nums.get(i).get(pos[i]);
                if (num < nums.get(minPos).get(pos[minPos])) minPos = i;
                if (num > nums.get(maxPos).get(pos[maxPos])) maxPos = i;
            }
            int[] range = new int[2];
            range[0] = nums.get(minPos).get(pos[minPos]);
            range[1] = nums.get(maxPos).get(pos[maxPos]);
            if (range[1] - range[0] < smallestRange[1] - smallestRange[0] ||
                    range[1] - range[0] == smallestRange[1] - smallestRange[0] && range[0] < smallestRange[0]) {
                smallestRange[0] = range[0];
                smallestRange[1] = range[1];
            }
            if (pos[minPos] == nums.get(minPos).size() - 1) {
                break;
            } else {
                pos[minPos]++;
            }
        }
        return smallestRange;
    }

    // ------------------------------------------------------------

    public int[] smallestRange3(List<List<Integer>> nums) {
        int[] smallestRange = new int[]{-100000, 100000};

        Map<List<Integer>, Integer> posMap = new HashMap<>();

        while (true) {
            nums.sort((o1, o2) -> {
                int pos1 = posMap.getOrDefault(o1, 0);
                int pos2 = posMap.getOrDefault(o2, 0);
                return o1.get(pos1) - o2.get(pos2);
            }); // 按照首位升序排列
//        System.out.println(nums);
            int[] range = new int[]{getValAtPos(nums, posMap, 0), getValAtPos(nums, posMap, nums.size() - 1)};
            if (range[1] - range[0] < smallestRange[1] - smallestRange[0] ||
                    range[1] - range[0] == smallestRange[1] - smallestRange[0] && range[0] < smallestRange[0]) {
                smallestRange[0] = range[0];
                smallestRange[1] = range[1];
            }
            List<Integer> smallest = nums.get(0);
            int pos = posMap.getOrDefault(smallest, 0);
            if (pos == smallest.size() - 1) {
                break;
            } else {
                posMap.put(smallest, pos + 1);
            }
        }

        return smallestRange;
    }

    private int getValAtPos(List<List<Integer>> nums, Map<List<Integer>, Integer> posMap, int pos) {
        List<Integer> arr = nums.get(pos);
        return arr.get(posMap.getOrDefault(arr, 0));
    }

    // ------------------------------------------------------------

    public int[] smallestRange4(List<List<Integer>> nums) {
        int[] smallestRange = new int[]{-100000, 100000};

        LinkedList<LinkedList<Integer>> arrList = genLinkedList(nums);
        arrList.sort(Comparator.comparingInt(LinkedList::getFirst)); // 按照首位升序排列
//        System.out.println("arrList: " + arrList);

        while (true) {
            LinkedList<Integer> smallest = arrList.getFirst();
            LinkedList<Integer> largest = arrList.getLast();
            int[] range = new int[]{smallest.getFirst(), largest.getFirst()};
            if (range[1] - range[0] < smallestRange[1] - smallestRange[0] ||
                    range[1] - range[0] == smallestRange[1] - smallestRange[0] && range[0] < smallestRange[0]) {
                smallestRange[0] = range[0];
                smallestRange[1] = range[1];
            }
            if (smallestRange[0] == smallestRange[1]) break; // 不可能再小了
            smallest.removeFirst(); // 删除最小项
            if (arrList.getFirst().size() == 0) break;
            resort(arrList);
//            System.out.println("=======================================");
        }

        return smallestRange;
    }

    private LinkedList<LinkedList<Integer>> genLinkedList(List<List<Integer>> nums) {
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        for (List<Integer> num : nums) {
            LinkedList<Integer> l0 = new LinkedList<>(num);
            list.addLast(l0);
        }
        return list;
    }

    private void resort(LinkedList<LinkedList<Integer>> arrList) {
        LinkedList.Node<LinkedList<Integer>> node = arrList.head.next;
        LinkedList<Integer> arr = node.val;
        int firstVal = arr.getFirst();
        while (node.next != null) {
//            System.out.println(arrList);
            LinkedList.Node<LinkedList<Integer>> next = node.next;
            if (next.val.getFirst() < firstVal) {
                // swap node and its next
                node.next = next.next;
                node.before.next = next;
                next.before = node.before;
                if (next.next != null) next.next.before = node;
                next.next = node;
                node.before = next;
                if (arrList.tail == next) arrList.tail = node;
            } else break;
        }
    }

    private static class LinkedList<T> {

        public static class Node<T> {
            Node(T val) {
                this.val = val;
            }

            T val;
            Node<T> next;
            Node<T> before;

            @Override
            public String toString() {
                return val + (next == null ? "" : ", " + next);
            }
        }

        Node<T> head;
        Node<T> tail;
        private int size;

        LinkedList() {
            head = new Node<>(null);
            tail = head;
        }

        LinkedList(List<T> origin) {
            this();
            for (T val : origin) {
                addLast(val);
            }
        }

        public T getFirst() {
            return head.next == null ? null : head.next.val;
        }

        public T getLast() {
            return tail.val;
        }

        public void addLast(T val) {
            Node<T> n = new Node<>(val);
            n.before = tail;
            tail.next = n;
            tail = n;
            size++;
        }

        public T removeFirst() {
            Node<T> first = head.next;
            if (first == null) return null;
            size--;
            Node<T> second = first.next;
            head.next = second;
            if (second != null) second.before = head;
            return first.val;
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        public void sort(Comparator<? super T> c) {
            Object[] a = toArray();
            Arrays.sort(a, ((Comparator) c));
            clear();
            for (Object o : a) {
                addLast((T) o);
            }
        }

        public int size() {
            return size;
        }

        public void clear() {
            head.next = null;
            tail = head;
            size = 0;
        }

        @Override
        public String toString() {
            return head.next == null ? "[]" : "[" + head.next.toString() + "]";
        }

        public Object[] toArray() {
            Object[] a = new Object[size];
            Node<T> p = head;
            int i = 0;
            while (p.next != null) {
                a[i] = p.next.val;
                i++;
                p = p.next;
            }
            return a;
        }
    }

    // ------------------------------------------------


    public static void main(String[] args) {
        int[][] testcase = new int[][]{{95387, 95790, 97307, 98168, 99868, 99995, 99995, 100000}, {-69454, -17042, 8172, 50983, 63432, 72854, 73012, 80848, 83723, 85916, 91759, 99779, 99913, 99944, 99994, 99999, 99999}, {65641, 95910, 97995, 98196, 98969, 99008, 99591, 99732, 99735, 99896, 99952, 99989, 99999, 100000}, {57459, 95855, 97360, 98320, 99147, 99865, 99955, 99989, 99997, 99998, 100000}, {-81589, -3474, 84141, 92981, 95255, 99192, 99962, 99970, 99994, 99998, 99999, 100000}, {-23262, 92924, 95548, 96462, 99338, 99553, 99555, 99569, 99644, 99903, 99909, 99999, 99999, 100000}, {-58466, 24432, 87898, 92795, 95701, 98143, 98163, 99182, 99351, 99746, 99811, 99943, 99955, 99978, 99997, 100000}, {-97588, 7867, 10356, 20288, 67836, 69868, 73038, 77753, 81937, 88474, 89979, 92182, 98091, 99635, 99902, 99941, 99975, 99987, 99991, 99998, 99998, 99998, 99998, 99998, 99999, 99999, 99999, 100000}, {-96955, 41521, 84537, 89794, 96226, 97103, 97490, 99347, 99957, 99997, 100000}, {-49247, 93963, 99006, 99428, 99964, 99992, 100000}, {46062, 48599, 95745, 98620, 98677, 99516, 99802, 99973, 99993, 100000}, {-3786, 59724, 62870, 80033, 90471, 98836, 99395, 99574, 99682, 99724, 99909, 99963, 99979, 99999, 100000}, {-62512, -19463, 84187, 89388, 91368, 95524, 98987, 99085, 99230, 99809, 99978, 100000}, {18183, 83019, 98718, 99570, 99777, 99980, 100000}, {19925, 20448, 81509, 93698, 98451, 98776, 98915, 99007, 99925, 99994, 99996, 99999, 100000}, {-96129, 93245, 95417, 98492, 99013, 99921, 99934, 99989, 99995, 100000}, {-25468, 61948, 68372, 85478, 91239, 98906, 98988, 99653, 99915, 99957, 99998, 99999, 99999, 100000}, {36648, 65266, 95679, 98905, 99868, 99977, 99983, 99983, 99995, 99995, 99996, 99997, 100000}, {56006, 78969, 86785, 89834, 92494, 93887, 98268, 99771, 99982, 99998, 99999, 100000}, {95387, 95790, 97307, 98168, 99868, 99995, 99995, 100000}, {-69454, -17042, 8172, 50983, 63432, 72854, 73012, 80848, 83723, 85916, 91759, 99779, 99913, 99944, 99994, 99999, 99999}, {65641, 95910, 97995, 98196, 98969, 99008, 99591, 99732, 99735, 99896, 99952, 99989, 99999, 100000}, {57459, 95855, 97360, 98320, 99147, 99865, 99955, 99989, 99997, 99998, 100000}, {-81589, -3474, 84141, 92981, 95255, 99192, 99962, 99970, 99994, 99998, 99999, 100000}, {-23262, 92924, 95548, 96462, 99338, 99553, 99555, 99569, 99644, 99903, 99909, 99999, 99999, 100000}};
        List<List<Integer>> tc = Util.arrToList(testcase);
        P632_SmallestRangeCoveringElementsFromKLists p = new P632_SmallestRangeCoveringElementsFromKLists();
        System.out.println(Arrays.toString(p.smallestRange4(tc)));
    }
}
