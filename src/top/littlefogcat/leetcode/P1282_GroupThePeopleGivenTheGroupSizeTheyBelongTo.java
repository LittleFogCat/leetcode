package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1282_GroupThePeopleGivenTheGroupSizeTheyBelongTo {
    public List<List<Integer>> groupThePeople0(int[] groupSizes) {
        List<List<Integer>> r = new ArrayList<>();
        Map<Integer, List<Integer>> sizePosMap = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            List<Integer> iList = sizePosMap.getOrDefault(size, new ArrayList<>());
            iList.add(i);
            sizePosMap.put(groupSizes[i], iList);
        }
        for (Integer size : sizePosMap.keySet()) {
            List<Integer> iList = sizePosMap.get(size);
            int start = 0;
            while (start < iList.size()) {
                r.add(iList.subList(start, start += size));
            }
        }
        return r;
    }

    @SuppressWarnings("unchecked")
    public List<List<Integer>> groupThePeople2(int[] groupSizes) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer>[] sizePosMap = new List[501];
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            if (sizePosMap[size] == null) sizePosMap[size] = new ArrayList<>();
            sizePosMap[size].add(i);
        }
        for (int i = 0; i < sizePosMap.length; i++) {
            if (sizePosMap[i] == null) continue;
            int start = 0;
            while (start < sizePosMap[i].size()) {
                r.add(sizePosMap[i].subList(start, start += i));
            }
        }
        return r;
    }

    @SuppressWarnings("unchecked")
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer>[] map = new List[501];
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            if (map[size] == null) map[size] = new ArrayList<>();
            map[size].add(i);
            if (map[size].size() == size) { // 凑够一组了
                r.add(map[size]);
                map[size] = new ArrayList<>();
            }
        }
        return r;
    }
}
