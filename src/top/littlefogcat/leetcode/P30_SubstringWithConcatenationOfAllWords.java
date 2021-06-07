package top.littlefogcat.leetcode;

import java.util.*;

@SuppressWarnings("unchecked")
public class P30_SubstringWithConcatenationOfAllWords {
    private LinkedList<String>[] tmpWords;

    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> r = new ArrayList<>();
        Arrays.sort(words);
        int size = words.length; // 单词数组的大小
        int len = words[0].length(); // 每个单词的长度
        int end = s.length() - size * len;
        tmpWords = new LinkedList[len];
        for (int i = 0; i <= end; i++) {
            // 以i为起点，分割size个单词出来。
            LinkedList<String> found = find(s, i, size, len);
            System.out.println(found);
            if (equals(words, found)) {
                r.add(i);
            }
        }
        return r;
    }

    /**
     * 以[start]为起点与以[start+len]为起点，结果只差一个单词。
     * 故可以将结果暂存。
     *
     * @return 字符串[s]中，以[start]为起点，以[len]为长度分割的数组。
     */
    public LinkedList<String> find(String s, int start, int size, int len) {
        int mod = start % len;
        if (tmpWords[mod] == null || tmpWords[mod].size() == 0) {
            tmpWords[mod] = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                int sta = start + i * len;
                int end = sta + len;
                String word = s.substring(sta, end);
                tmpWords[mod].addLast(word);
            }
        } else {
            tmpWords[mod].removeFirst();
            tmpWords[mod].addLast(s.substring(start + len * (size - 1), start + len * size));
        }
        return tmpWords[mod];
    }

    private boolean equals(String[] words, LinkedList<String> found) {
        String[] copy = found.toArray(new String[0]);
        Arrays.sort(copy);
        return Arrays.equals(words, copy);
    }

    // =========================================================

    public List<Integer> findSubstring2(String s, String[] words) {
        // 返回结果
        List<Integer> r = new ArrayList<>();
        // 将words数组转换为map
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        // 分割字符串
        int wordLen = words[0].length();
        int size = words.length;
        for (int first = 0; first < wordLen; first++) {
            // first: 第一个单词的起始位置
            Map<String, Integer> splitMap = new HashMap<>(); // 分割数组
            // 初始化分割数组
            for (int i = 0; i < size; i++) {
                int start = first + i * wordLen; // 单词起始位置
                if (start + wordLen > s.length()) break;
                String word = s.substring(start, start + wordLen);
                splitMap.put(word, splitMap.getOrDefault(word, 0) + 1);
            }
            if (splitMap.equals(wordsMap)) r.add(first);
            // 依次循环更新分割数组
            // 去掉头一个单词，尾部加上后一个单词
            int lastStart = s.length() - size * wordLen;
            for (int i = first + wordLen; i <= lastStart; i += wordLen) {
                String delete = s.substring(i - wordLen, i); // 头一个单词去掉
                int start = i + size * wordLen - wordLen;
                int end = start + wordLen;
                if (end > s.length()) break;
                String add = s.substring(start, end); // 后一个单词加上
                int deleteVal = splitMap.get(delete);
                if (deleteVal == 1) splitMap.remove(delete); // 不要有0值的存在，直接删除
                else splitMap.put(delete, deleteVal - 1);
                splitMap.put(add, splitMap.getOrDefault(add, 0) + 1); // 添加最后一个单词
                if (splitMap.equals(wordsMap)) r.add(i);
            }
        }

        return r;
    }

    // ---------------------------------------------------------

    // 逆向遍历
    public List<Integer> findSubstring3(String s, String[] words) {
        // 返回结果
        List<Integer> r = new ArrayList<>();
        // size: 关键字个数; wordLen: 每个关键字的长度;
        int size = words.length;
        int wordLen = words[0].length();
        // 将关键字数组转换为map
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        // 分割字符串
        for (int offset = 0; offset < wordLen; offset++) {
            for (int start = offset; start <= s.length() - size * wordLen; start += wordLen) {
                // start: 分割单词集合第一项的位置
                Map<String, Integer> subMap = new HashMap<>(); // 单词计数map
                int wordCount = 0;
                int lastWord = start + (size - 1) * wordLen; // 最后一个单词的序号
                if (!wordsMap.containsKey(s.substring(start, start + wordLen))) continue;
                // 从后往前遍历子字符串
                for (int pos = lastWord; pos >= start; pos -= wordLen) {
                    String word = s.substring(pos, pos + wordLen);
                    int count = subMap.getOrDefault(word, 0);
                    int maxCount = wordsMap.getOrDefault(word, 0);
                    if (count >= maxCount) { // 单词总超过上限，放弃当前项
                        start = pos;
                        break;
                    }
                    // 添加单词到Map中
                    subMap.put(word, count + 1);
                    wordCount++;
                    if (wordCount == size) {
                        r.add(start);
                    }
                }
            }
        }
        return r;
    }

    // ------------------------------------------------------------------

    public void test(String s, int start, int len, Map<String, Integer> wordsMap, List<Integer> result, int size) {
        Map<String, Integer> subMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int pos = start + i * len;
            String word = s.substring(pos, pos + len);
            subMap.put(word, subMap.getOrDefault(word, 0) + 1);
        }
        if (subMap.equals(wordsMap)) {
            result.add(start);
        }
    }

    // 正向遍历
    public List<Integer> findSubstring(String s, String[] words) {
        // 返回结果
        List<Integer> r = new ArrayList<>();
        // 将关键字数组转换为map
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        // 分割字符串
        int size = words.length;
        int wordLen = words[0].length();
        for (int offset = 0; offset < wordLen; offset++) {
            Map<String, Integer> subMap = new HashMap<>(); // subMap
            for (int start = offset, last = offset; last <= s.length() - wordLen; last += wordLen) {
                String word = s.substring(last, last + wordLen); // 截取单词
                if (!wordsMap.containsKey(word)) { // word不是关键词，直接从下一个单词重新开始
                    subMap.clear();
                    start = last + wordLen;
                    continue;
                }
                int count = subMap.getOrDefault(word, 0); // 当前单词已经出现的次数
                if (count >= wordsMap.get(word)) { // 如果该词个数已经到上限了，删除之
                    for (; ; ) { // 删除第一次出现的这个单词及之前的所有单词
                        String delete = s.substring(start, start + wordLen);
                        start += wordLen;
                        int cnt = subMap.get(delete);
                        if (cnt == 1) subMap.remove(delete);
                        else subMap.put(delete, cnt - 1);
                        if (delete.equals(word)) break;
                    }
                    count--;
                }
                subMap.put(word, count + 1);
                int wordCount = (last - start) / wordLen + 1; // 已经寻觅到的关键词个数
                if (wordCount == size) r.add(start); // 单词总数相同，代表两个Map相同
            }
        }
        return r;
    }
    // ---------------------------------------

    // 他人做法
    public List<Integer> findSubstring4(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordLen = words[0].length();
        int len = words.length;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int window = wordLen * len;
        for (int i = 0; i < wordLen; i++) {
            for (int j = i; j <= s.length() - window; j += wordLen) {
                Map<String, Integer> used = new HashMap<>();
                int startIndex = j + wordLen;
                for (int k = j + window; k > j; k -= wordLen) {
                    int beginIndex = k - wordLen;
                    String subsub = s.substring(beginIndex, k);
                    int use = used.getOrDefault(subsub, 0) + 1;
                    if (use > map.getOrDefault(subsub, 0)) {
                        j = beginIndex;
                        break;
                    }
                    if (k == startIndex) res.add(j);
                    else used.put(subsub, use);
                }
            }
        }

        return res;
    }


}
