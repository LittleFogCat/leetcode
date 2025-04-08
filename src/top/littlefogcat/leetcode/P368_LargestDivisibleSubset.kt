package top.littlefogcat.leetcode

import java.util.*

fun main() {
    println("Hello")
}

private class Solution {

    var map: MutableMap<Int, List<Int>> = HashMap()

    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        Arrays.sort(nums)
        var largest: List<Int>? = null
        for (i in nums.indices) {
            val subset = getLargestSubset(nums, i)
            if (largest == null || largest.size < subset.size) {
                largest = subset
            }
        }
        return largest?.map { nums[it] } ?: emptyList()
    }

    fun getLargestSubset(nums: IntArray, baseIndex: Int): List<Int> {
        // 以baseIndex为基础的最大子序列
        if (map[baseIndex] != null) {
            return map[baseIndex]!!
        }

        var next: List<Int>? = null

        for (i in baseIndex + 1 until nums.size) {
            if (nums[i] % nums[baseIndex] == 0) {
                val iSubset = getLargestSubset(nums, i)

                if (next == null || next.size < iSubset.size) {
                    next = iSubset
                }
            }
        }

        val result: List<Int>
        if (next == null) {
            result = Collections.singletonList(baseIndex)
        } else {
            result = arrayListOf()
            result.add(baseIndex)
            result.addAll(next)
        }
        map[baseIndex] = result
        return result
    }
}

private class Solution2 {

    val countMap = mutableMapOf<Int, Int>()
    val nexts = mutableMapOf<Int, Int>()

    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        Arrays.sort(nums)
        // 第一遍先计算数量
        var start = 0
        var maxLen = 0
        for (i in nums.indices) {
            val len = getLargestCount(nums, i)
            if (len >= maxLen) {
                maxLen = len
                start = i
            }
        }

        val result = arrayListOf<Int>()
        var p = start
        while (true) {
            result.add(nums[p])
            p = nexts[p] ?: break
        }
        return result
    }

    fun getLargestCount(nums: IntArray, baseIndex: Int): Int {
        if (countMap[baseIndex] != null) {
            return countMap[baseIndex]!!
        }
        var max = 0
        var next = -1
        for (i in baseIndex + 1 until nums.size) {
            if (nums[i] % nums[baseIndex] == 0) {
                val iMax = getLargestCount(nums, i)
                if (iMax > max) {
                    max = iMax
                    next = i
                }
            }
        }
        if (next != -1) {
            nexts[baseIndex] = next
        }
        val r = max + 1
        countMap[baseIndex] = r
        return r
    }

}