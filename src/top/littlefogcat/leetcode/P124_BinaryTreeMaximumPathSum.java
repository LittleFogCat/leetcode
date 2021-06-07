package top.littlefogcat.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P124_BinaryTreeMaximumPathSum {
    Map<TreeNode, Integer> map = new HashMap<>();

    public int maxPathSum(TreeNode root) {
        // 以某个节点为起始的最大单向路径和
        mps(root);
        System.out.println(mapToString(map));
        int max = root.val;
        for (Map.Entry<TreeNode, Integer> e : map.entrySet()) {
            TreeNode node = e.getKey();
            int nodeMax = node.val
                    + Math.max(0, map.getOrDefault(node.left, 0))
                    + Math.max(0, map.getOrDefault(node.right, 0));
            if (nodeMax > max) max = nodeMax;
        }
        return max;
    }

    private String mapToString(Map<TreeNode, Integer> map) {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<TreeNode, Integer> en : map.entrySet()) {
            TreeNode key = en.getKey();
            s.append(key.val).append(":").append(en.getValue()).append(" ");
        }
        return s.toString();
    }

    // 单向最大路径
    private int mps(TreeNode root) {
        if (map.containsKey(root)) return map.get(root);
        int mps;
        if (root.left == null && root.right == null) {
            mps = root.val;
        } else if (root.left == null) {
            mps = Math.max(root.val, root.val + mps(root.right));
        } else if (root.right == null) {
            mps = Math.max(root.val, root.val + mps(root.left));
        } else {
            mps = Math.max(Math.max(root.val, root.val + mps(root.left)),
                    root.val + mps(root.right));
        }
        map.put(root, mps);
        return mps;
    }

}
