package top.littlefogcat.leetcode.structs.unionfind;

import top.littlefogcat.leetcode.structs.UnionFind;

/**
 * 例题
 * > 输入一个整型数组`int[] cities`，每个元素代表一个城市；
 * > 输入若干对整数`int[][] roads`，每对整数表示对应的两个城市之间修建有**道路**。
 * > 如果城市之间能通过道路到达，那么称这些城市处于同一个**道路系统**。
 * > 问：总共有多少个**道路系统**？
 */
public class Sample {
    public int countRoadSystem(int[] cities, int[][] roads) {
        UnionFindInt uf = new UnionFindInt(cities);
        for (int[] road : roads) uf.union(road[0], road[1]);
        return uf.size();
    }
}
