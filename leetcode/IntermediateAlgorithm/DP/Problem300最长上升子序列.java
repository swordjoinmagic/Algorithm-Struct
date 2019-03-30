package leetcode.IntermediateAlgorithm.DP;

import java.util.Arrays;

/**
 *
 * 思路：
         1.设f[i]为从下标i开始的最长不降子序列
         从数组的最后开始找（即从下标为len-1开始）,
         设当前下标为i，
        那么要向后（保证下标比i大）找到一个比nums[i]大，
         且目前最长不降子序列长度最长的值。
         时间复杂度O(n2)

        2.
 *
 */
public class Problem300最长上升子序列 {

    public static void main(String[] args){
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int result = new Problem300最长上升子序列().lengthOfLIS(nums);
        System.out.println(result);
    }

    public int lengthOfLIS(int[] nums) {

        if(nums.length==0) return 0;

        int[] f = new int[nums.length];

        /*
            从数组的最后开始找,
            设当前下标为i，那么要找到一个比nums[i]大，
            且目前最长不降子序列长度最长的值。
            时间复杂度O(n2)
        */

        // 初始化
        Arrays.fill(f,1);

        int max = 1;

        for(int i=nums.length-2;i>=0;i--){

            for(int j=i+1;j<nums.length;j++){

                if(nums[j]>nums[i])
                    f[i] = Math.max(f[i],f[j]+1);

                max = Math.max(max,f[i]);
            }

            print(f);
        }

        return max;
    }

    public void print(int[] nums){
        for(int data : nums) System.out.print(data+" ");
        System.out.println();
    }
}
