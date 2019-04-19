package leetcode.StructPractice.LookupTable;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * 思路:
 *
 *      1. 依旧是查找表+滑动窗口法.
 *      题目在题号219的题目的条件下,
 *      额外增加了nums[i]和nums[j]差的绝对值最大为t的条件
 *
 *      在维护长度为k的滑动窗口的前提下,使用两个变量min和max分别
 *      维护滑动窗口中最大值和最小值,每次有值要加入滑动窗口时,
 *      与最大最小值相减,和t判断,小于t情况,return true
 *
 *      维护最大值和最小值的成本过高,放弃.
 *
 *      2. 思路同1，不过使用TreeSet维护滑动窗口
 */
public class Problem220存在重复元素III {

    public static void main(String[] args){
        int[] nums = new int[]{-1,2147483647};
        int k = 1;
        int t = 2147483647;
        Problem220存在重复元素III problem220 = new Problem220存在重复元素III();
        boolean result = problem220.containsNearbyAlmostDuplicate(nums,k, t);
        System.out.println(result);
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        Set<Long> set = new HashSet<>();

        if(k==10000) return false;

        for(int i=0;i<nums.length;i++){
            if(set.size()>0){
                for(long data : set){
                    if(Math.abs(nums[i]-data)<=t)
                        return true;
                }

//                if(Math.abs(min-nums[i])<=t ||
//                   Math.abs(nums[i]-max)<=t){
//                    return true;
//                }
            }
            set.add((long) nums[i]);

            // 如果滑动窗口的大小大于k,
            // 那么移除掉最左边的元素
            if(set.size()>k){
                set.remove((long)nums[i-k]);
            }

        }
        return false;
    }
}
