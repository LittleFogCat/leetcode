package top.littlefogcat.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P811_SubdomainVisitCount {
    class Solution {
        private Map<String, Integer> visitMap = new HashMap<>();

        public List<String> subdomainVisits(String[] cpdomains) {
            for (String cpdomain : cpdomains) {
                int index = cpdomain.indexOf(' ');
                int visit = Integer.parseInt(cpdomain.substring(0, index));
                String domain = cpdomain.substring(index + 1);
                add(domain, visit);
            }
            List<String> results = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : visitMap.entrySet()) {
                String result = entry.getValue() + " " + entry.getKey();
                results.add(result);
            }
            return results;
        }

        private void add(String domain, int visit) {
            while (domain.contains(".")) {
                visitMap.put(domain, visitMap.getOrDefault(domain, 0) + visit);
//                visitMap.merge(domain, visit, (oldVisit, v) -> oldVisit + v);
                domain = domain.substring(domain.indexOf(".") + 1);
            }
            visitMap.put(domain, visitMap.getOrDefault(domain, 0) + visit);
        }
    }
}
