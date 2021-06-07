package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P590_N_AryTreePostorderTraversal {
    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

    class Solution {
        private List<Integer> result = new ArrayList<>();

        public List<Integer> postorder(Node root) {
            if (root == null) return null;
            if (root.children == null || root.children.isEmpty()) {
                result.add(root.val);
                return result;
            } else {
                for (Node child : root.children) {
                    postorder(child);
                }
                result.add(root.val);
                return result;
            }
        }
    }

    class Solution1 {
        private List<Integer> result = new ArrayList<>();

        public List<Integer> postorder(Node root) {
            if (root == null) return result;
            List<Node> nodesToAdd = new ArrayList<>();
            while (true) {

            }
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
