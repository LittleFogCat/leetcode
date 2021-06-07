package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.List;

public class P71_SimplifyPath {
    public String simplifyPath(String path) {
        List<String> paths = new ArrayList<>();
        String next;
        out:
        for (int start = 0, end; start < path.length(); start = end + 1) {
            while (path.charAt(start) == '/') {
                start++;
                if (start >= path.length()) break out;
            }
            for (end = start + 1; end != path.length() && path.charAt(end) != '/'; end++) ;
            next = path.substring(start, end);
            if ("..".equals(next)) {
                if (paths.size() > 0) paths.remove(paths.size() - 1);
            } else if (!".".equals(next)) {
                paths.add(next);
            }
        }
        if (paths.isEmpty()) return "/";
        StringBuilder s = new StringBuilder();
        for (String dir : paths) {
            s.append("/").append(dir);
        }
        return s.toString();
    }
}
