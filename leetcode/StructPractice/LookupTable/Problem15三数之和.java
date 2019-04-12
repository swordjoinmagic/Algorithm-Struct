package leetcode.StructPractice.LookupTable;

import leetcode.Other.Problem152乘积最大子序列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ○ 本题是第二次做
 *
 * × 在第二次做的时候,没有思路或思路不清晰
 *
 * 思路：
 *      1. 三重遍历,时间复杂度O(n3)
 *      2. 先对数组进行排序.
 *      遍历a，则问题转化为找到b,c使得b+c=-a，此时
 *      问题就被转化为两数之和问题，使用对撞指针解决
 *
 *      时间复杂度O(N2logN)
 *
 */
public class Problem15三数之和 {

    public static void main(String[] args){
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        Problem15三数之和 problem15 = new Problem15三数之和();
        List<List<Integer>> result = problem15.threeSum(nums);
        System.out.println(result);
    }

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // 先对数组进行排序
        Arrays.sort(nums);


        // 遍历a
        for(int i=0;i<nums.length-1;i++){

            int a = nums[i];
            // 这里注意nums是已排序数组,所以重复数字只可能堆在一起
            // 如果和前面的数相等,就跳过,因为不能把相同的数字fix两次
            if(i>0 && nums[i]==nums[i-1]) continue;
            if(a>0) break;  // 剪枝

            // 对撞指针找b,c,范围是在a前面的区域
            int left = i+1;
            int right = nums.length-1;
            int target = -a;
            while (left<right){
                int number = nums[left]+nums[right];
                if(number>target){
                    right--;
                }else if(number<target){
                    left++;
                }else {
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    // 跳过重复数字
                    while (left<right && nums[left]==nums[left+1]) left++;
                    while (left<right && nums[right]==nums[right-1]) right--;
                    left++;
                    right--;
                }
            }

        }
        return result;
    }
}
