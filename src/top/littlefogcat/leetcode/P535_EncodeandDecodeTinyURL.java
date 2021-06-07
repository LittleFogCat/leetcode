package top.littlefogcat.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class P535_EncodeandDecodeTinyURL {
    public class Codec {
        private final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        private final Map<String, String> urlMap = new HashMap<>();
        private final Random random = new Random();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            StringBuilder shortUrl = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                shortUrl.append(chars[random.nextInt(chars.length)]);
            }
            if (urlMap.get(shortUrl.toString()) != null) {
                return encode(longUrl);
            }
            urlMap.put(shortUrl.toString(), longUrl);
            return shortUrl.toString();
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return urlMap.get(shortUrl);
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
}
