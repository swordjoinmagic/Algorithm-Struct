package leetcode.StructPractice.ArrayAndString;

import com.sun.org.apache.bcel.internal.generic.FALOAD;

import java.util.Arrays;

/**
 * 思路：
 *      1. 暴力。遍历一遍，找到数组最大值。
 *      再次遍历一遍，判断最大值是否是其他每个数字的两倍。
 *      时间复杂度O(N)
 */
public class Problem747至少是其他数字两倍的最大数 {

    public static void main(String[] args){
        Problem747至少是其他数字两倍的最大数 problem747 = new Problem747至少是其他数字两倍的最大数();
        int[] nums = {0,0,0,1};
        int result = problem747.dominantIndex(nums);
        System.out.print(result);
    }

    public int dominantIndex(int[] nums) {
        int max = 0;
        int index = -1;
        for(int i=0;i<nums.length;i++){
            int data = nums[i];
            if(data>max) {
                max = Math.max(max, data);
                index = i;
            }
        }
        for(int data : nums) if(data!=max && data*2>max) return -1;
        return index;
    }
}
