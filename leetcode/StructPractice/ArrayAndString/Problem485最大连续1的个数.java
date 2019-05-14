package leetcode.StructPractice.ArrayAndString;

/**
 * 思路:
 *      1. 模拟.扫一遍,遇1加1,遇非1清0
 */
public class Problem485最大连续1的个数 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;

        int temp = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                max = Math.max(max,temp);
                temp = 0;
            }else {
                temp ++;
            }
        }

        max = Math.max(temp,max);

        return max;
    }
}
