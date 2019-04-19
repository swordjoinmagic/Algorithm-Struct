package leetcode.StructPractice.LookupTable;

import java.util.*;

/**
 * 思路:
 *
 *      1. 用map.
 *      遍历一遍数组,并将其以 值(key)-下标(value)的形式记录在map中
 *
 *      根据题意,i和j的差的绝对值最大为k,即*
 *      nums[i]-nums[j] = k or nums[i]-nums[j] = -k,
 *
 *      遍历一遍i,找在map中是否存在
 *      值nums[j] = k-nums[i] or nums[j] = -k-nums[i]
 *
 *      时间复杂度O(N)
 *
 *      PS: 这里需要注意的是,数组中不同下标的值可能是相同的,
 *      这个时候,将Map的值(value)变为List,每个元素存储一个下标
 *
 *      思路1错了,连题都没看清.......→_→
 *
 *      2. 题目要求求得索引i,j,使得nums[i]==nums[j],
 *      且i和j的差值最大为k
 *
 *      时间复杂度O(n)~O(n2)
 *
 *      3. 滑动窗口+查找表.
 *
 *      题目要求i和j的差值最大为k,那么可以定义滑动窗口长度为K,
 *      查找表记录滑动窗口中的数.
 *
 *      步骤如下:
 *
 *      a. 定义集合set,用变量i遍历数组,每次遍历到的值加入set
 *
 *      b. 当前数已经在集合中时,返回true,否则继续遍历
 *
 *      c. 当set(即滑动窗口)的大小大于k时,移除最左边的数
 */
public class Problem219存在重复元素II {

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,1,2,3};
        int k = 2;
        boolean result = new Problem219存在重复元素II().containsNearbyDuplicate(nums,k);
        System.out.println(result);
    }

    public boolean Slove1(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            int value = nums[i];
            if(!map.containsKey(value))
                map.put(value,new ArrayList<>());
            map.get(value).add(i);
        }

        for(int i=0;i<nums.length;i++){

            if(map.containsKey(nums[i])){
                for(int j : map.get(nums[i])){
                    if(i==j) continue;
                    if(Math.abs(i-j)<=k)
                        return true;
                }
            }

        }

        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
            if(set.size()>k){
                // 移除最左边的数
                set.remove(nums[i-k]);
            }
        }

        return false;
    }
}
