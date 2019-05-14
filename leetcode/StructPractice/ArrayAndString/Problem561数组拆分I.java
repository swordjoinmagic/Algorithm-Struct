package leetcode.StructPractice.ArrayAndString;

import java.util.Arrays;

/**
 * 思路:
 *      1. 先排序,然后两两自成一队.
 */
public class Problem561数组拆分I {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int result = 0;

        for(int i=0;i<nums.length;i+=2){
            result += Math.min(nums[i],nums[i+1]);
        }
        return result;
    }
}
