# leetcode
Leetcode Java

## 部分简要思路


#### P053_MaximumSubarray
经典的DP最大子序列和。

对一个序列[a1, a2, a3, ..., an]，要求最大子序列和，可以等价于求max(以a1为起点的序列的最大序列和，以a2为起点的序列的最大和，...，以an为起点的序列的最大和)。而对于第i项ai，不难推出，以ai为起点的序列最大和为：
max_sum(i) = max(ai, max_sum(i+1) + ai)
然后递推出max_sum(1)，也就是最后的结果了。

依此，我们可以写一个for循环，从数组的最后往前，依次递推，并保存最大值：
```
        public int maxSubArray(int[] nums) {
            int max = nums[nums.length - 1];
            int lastMaxSum = nums[nums.length - 1];

            for (int i = nums.length - 2; i >= 0; i--) {
                int ai = nums[i];
                lastMaxSum = lastMaxSum > 0 ? ai + lastMaxSum : ai;
                max = Math.max(max, lastMaxSum);
            }
            return max;
        }
```


#### P338_CountingBits
计算一个数的二进制有几个1，很简单；

可以用位运算
```
while(i != 1){
	count += i & 1;
	i >>= 1;
}
```

#### P344_ReverseString
反转字符串。

首先使用`s.toCharArray()`把字符串转换成数组，然后左右交换，再转换回字符串即可。


#### P419_BattleshipsInABoard
比较有趣的一道题。关键是题目要求：

> Could you do it in **one-pass**, using only **O(1) extra memory** and **without modifying** the value of the board?
> 要求**一次遍历**，并且占用**常量空间**，且**不改变**原来的值。

很容易陷入这样的误区：想找一艘船，于是就去找出所有能够表示一条船的连续的区域，这样问题就特别复杂了。其实仔细一想，假如要数一笼鸡共有多少只，我们只用数有多少个头就行了，而不用把每只鸡观察的细致入微；同样的道理，要数有多少只船，只用找到有多少个船头（尾）就可以了。这样问题就可以简化成找船头（尾）的个数了，遍历所有的点，找出一共有多少符合格式的即可。

#### P461_HammingDistance
求汉明距离。

用异或。


#### P476_NumberComplement
求二进制按位取反。

直接用`~n`会得到负数，因为前面全部填1了。把前面填的1全部改成0就是最终结果了。
或者按照正常求二进制的方法，遇到0和1的操作反过来，亦可得到结果。


#### P500_KeyboardRow
给出字符串，求其所有字母是否在键盘的同一行上。

逐一检查字符串的每个字母是否和`s.charAt(0)`在同一行即可。
我是用HashMap做的。其实用字符串足够了。

#### P575_DistributeCandies
实际上就是求数组中一共有多少不同的数。

这种去重的问题很容易想到用HashSet去做，事实上这的确是最正确的方法之一，用Java最简单的实现也只用3行代码。当然，这么做的话，即使经过优化，提交的runtime大概在80多ms，而最快答案是28ms，作为一个稍微强迫的人，自然不能放过。略加思索，想起之前使用数组替代HashMap的方法，于是实现了一下，果然提高到了40+ms。代码大概如此：
```
        private int[] map = new int[200001];

        public int distributeCandies(int[] candies) {
            int current = 0;
            int max = candies.length / 2;
            for (int i : candies) {
                int realIndex = i + 100000;
                if (map[realIndex] == 0) {
                    map[realIndex] = 1;
                    current++;
                    if (current >= max) {
                        break;
                    }
                }
            }
            return current;
        }
```
事实上，map的类型使用int[]过于浪费了，这也是我考虑的疏漏。如果使用byte[]或者boolean[]的话，就是最快的答案了。（当然实际情况中更多还是使用Set吧，毕竟不是所有情况下都会给出明确的边界条件的）


#### 561. Array Partition I
> Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

>Example 1:
>>Input: [1,4,3,2]

>>Output: 4
>>Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

>Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].

**题目：**
给定2n个整数，将其分成n组，使得每组较小值之和最大。求出这个和。

**分析：**
这道题更像是一道数学题。如何才能最大？
通过简单的论证，可以得出结论，将所有整数从小到大排列，然后按顺序两两分组，即是最终所求的分组。用通俗的话讲，将数组排序，序号为偶数的项，即a[0]、a[2]、a[4]、……、a[2n-2]，之和就是最后的结果。证明过程还是比较简单的。
这样看来，把数组排个序再取出偶序号项求个和就行了。写了个简单的交换排序，然后提示TLE……好的吧，至少证明结果是正确的。不过果然n<sup>2</sup>效果差，稍微优化了一下步骤，倒是接受了，不过运行时间是这样的……
![Runtime](http://upload-images.jianshu.io/upload_images/6532223-ae71369428f459ba?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
不用说，最高的那个峰肯定是优化过的排序算法，运算时间36ms左右。但是让我好奇的是，在17ms附近还有一个更快的算法。假设上面的分析是正确的，难道不用排序就可以从小到大遍历数组？
首先遍历数组这个O(n)操作是跑不掉的，那么每一步操作的时间能控制在O(1)吗？一想到O(1)，脑中自然而然就浮现出了用空间换时间的万金油——HashMap。由于题目中给定的整数范围是-10000到10000，并不是很大，那么使用map这个办法也是行得通的。
把每个整数作为序号，将其出现的次数映射到一个数组中，然后只需要遍历一次这个数组就相当于从小到大遍历源数组了。于是重写代码：
```
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
```
典型的空间换时间。虽然或许有一些取巧，但是当数组长度很大，并且整数范围较小且确定的时候，这个办法是可行性和效率俱优的。
**他人解法**：
17ms的用的是上述map的办法，略过。
30+ms的看了下，自然就是排序了，用的都是Java中的Arrays.sort(nums)方法，这个方法使用的一种叫TimSort的排序算法。这里面分析起来又是一篇文章，此处略过。


#### 669. Trim a Binary Search Tree
二叉查找树根据范围剪枝。给定L，R（L<=R），只保留[L,R]范围内的节点。

这道题想通了就很简单，想不通就很麻烦，唯一的难度就在于想清楚的过程。返回剪枝后的结果，那我们要怎么剪？看看最小子问题：一个节点和他的左节点，右节点，如何来剪？

1. 如果这个节点小于L，那么所有左节点必然小于L，那么左儿子直接置空，同时由于该节点也应该删除，那么就使用剪枝后的右儿子代替该节点；
2. 右节点同理，如果值大于R，那么直接使用剪枝后的左儿子取代该节点；
3. 如果该节点的值在范围内，那么就保留该点，并对其左右儿子分别进行剪枝；

代码非常简单
```
        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) {
                return null;
            } else if (root.val < L) {
                return trimBST(root.right, L, R);
            } else if (root.val > R) {
                return trimBST(root.left, L, R);
            } else {
                root.left = trimBST(root.left, L, R);
                root.right = trimBST(root.right, L, R);
            }
            return root;
        }
```


#### 760. Find Anagram Mappings
>Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.

>We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.

>These lists A and B may contain duplicates. If there are multiple answers, output any of them.

>For example, given

>>A = [12, 28, 46, 32, 50]
B = [50, 12, 32, 46, 28]
We should return
[1, 4, 3, 2, 0]
as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.
Note:

>A, B have equal lengths in range [1, 100].
A[i], B[i] are integers in range [0, 10^5].

**题目：**
给出2个数组A和B，其中B是A乱序后的结果。求出A中的每个元素在B中的序号。
**分析：**
最暴力，最简单。2个for循环了事：
```
    public int[] anagramMappings(int[] A, int[] B) {
        int [] order = new int[A.length];
        
        for(int a1 = 0; a1 < A.length; a1++) {
            for(int b1 = 0; b1 < B.length; b1++){
                if(B[b1] == A[a1]){
                    order[a1] = b1;
                    break;
                }
            }
        }
        
        return order;
    }
```
一般来讲，傻瓜式的for循环都不是最优解。由于题目较新，看不到运行时间排行，就看了下讨论里面的，几乎是清一色的万能HashMap。的确，HashMap解决这种映射类的题目具有得天独厚的优势。另外还有使用排序的，没仔细看，暂略。



