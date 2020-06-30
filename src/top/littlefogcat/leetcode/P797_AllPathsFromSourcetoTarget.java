package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P797_AllPathsFromSourcetoTarget {

    public class Solution {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> result = new ArrayList<>();
            findRoute(new ArrayList<>(), 0, graph, result);
            return result;
        }

        /**
         * dfs
         *
         * @param before  这条路径已经经过的点
         * @param current 当前节点的序号
         * @param graph   图
         * @param result  返回的结果
         */
        private void findRoute(List<Integer> before, int current, int[][] graph, List<List<Integer>> result) {
            List<Integer> beforE = new ArrayList<>(before);
            beforE.add(current);
            if (graph[current].length == 0) {
                //到头了
                result.add(beforE);
            } else for (int next : graph[current]) {
                findRoute(beforE, next, graph, result);
            }
        }
    }
}