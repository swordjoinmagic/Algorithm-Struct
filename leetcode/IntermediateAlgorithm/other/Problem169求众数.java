package leetcode.IntermediateAlgorithm.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 *      1. 遍历一遍，使用HashMap存储数据，时间复杂度O(N)
 *      2.
 */
public class Problem169求众数 {

    public static void main(String[] args){
        int[] nums = new int[]{3,2,3};
        Problem169求众数 problem169 = new Problem169求众数();
        int result = problem169.majorityElement(nums);
        System.out.println(result);
    }

    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        int majorityNumber = 0;
        for(int data : nums){
            int n = map.getOrDefault(data,0)+1;
            map.put(data,n);
            if(n>max){
                max = n;
                majorityNumber = data;
            }
        }
        return majorityNumber;
    }
}
