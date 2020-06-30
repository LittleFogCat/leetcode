package top.littlefogcat.leetcode;

public class P561_ArrayPartitionI {
    
    class Solution {
        public int arrayPairSum(int[] nums) {
            int[] map = new int[20001]; // map中存放的是index这个数出现的"次数"

            for(int i :nums){
                map[i + 10000]++;
            }

            int k = 0; // 第k小的数。只有当k为偶数的时候，sum才增加
            int sum = 0;
            for(int i = 0;i < map.length;i++){
                int i1 = map[i]; // 次数
                if(i1 == 0){
                    continue;
                }
                if(k % 2 == 0){
                    sum += (i1 + 1) / 2 * (i -10000);
                }else{
                    sum += i1 /2 * (i - 10000);
                }
                k += i1;
            }
            return sum;
        }

    }
}
