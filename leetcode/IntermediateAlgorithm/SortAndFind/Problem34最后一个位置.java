package leetcode.IntermediateAlgorithm.SortAndFind;

/**
 * 思路：
 *      1. 二分法.找目标值在数组中的开始位置,二分的参数是left=0,end=nums.length-1
 *      找目标值在数组中的结束位置,二分参数是 startIndex+1,nums.length-1
 */
public class Problem34最后一个位置 {

    public static void main(String[] args){
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int[] array = new Problem34最后一个位置().searchRange(nums,target);
        for(int data : array){
            System.out.println(data);
        }
    }

    public int[] searchRange(int[] nums, int target) {

        return nums;
    }


}
