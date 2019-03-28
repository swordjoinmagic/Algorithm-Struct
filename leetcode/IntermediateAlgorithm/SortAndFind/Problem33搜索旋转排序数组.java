package leetcode.IntermediateAlgorithm.SortAndFind;

import review.Tree.BinarySortTree;

import java.util.Arrays;

/**
 * 题目要求：
 *  时间复杂度O(logN)
 *
 *  思路：
 *      1. 二分。参考评论区思路：
 *      将数组一分为二，
 *      其中一定有一个是有序的，
 *      另一个可能是有序，也能是部分有序。
 *      此时有序部分用二分法查找。
 *      无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。
 *      就这样循环.
 *
 */
public class Problem33搜索旋转排序数组 {

    public static void main(String[] args){
        Problem33搜索旋转排序数组 problem33 = new Problem33搜索旋转排序数组();
        int[] array = new int[]{4,5,6,7,0,1,2};
        int result = problem33.search(array,0);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {
        return BinarySearch(nums,target,0,nums.length);
    }

    public int BinarySearch(int[] nums,int target){

        // 0个元素的情况
        if(nums.length==0) return -1;
        // 边界判断，只有一个元素的情况
        if(nums.length==1)
            if(nums[0]==target) return 0;
            else return -1;

        // 首先判断数组是否有序（拿第一个元素和最后一个元素做比较）
        if(nums[0]>nums[nums.length-1]) {
            // 无序数组，需将此数组一分为二

            // 从中间切开此数组
            int mid = nums.length/2;

            int[] leftNums = Arrays.copyOfRange(nums,0,mid);
            int[] rightNums = Arrays.copyOfRange(nums,mid,nums.length);

            int leftResult = BinarySearch(leftNums,target);
            int rightResult = BinarySearch(rightNums,target);

            if(leftResult!=-1) return leftResult;
            else if(rightResult!=-1) return mid+rightResult;

            // 左右两边都找不到结果，返回-1
            return -1;
        }

        // 有序数组，二分扫描判断该有序数组是否存在值target
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return -1;
    }

    // end不包括
    public int BinarySearch(int[] nums,int target,int start,int end){

        // 0个元素的情况
        if(nums.length==0) return -1;
        // 边界判断，只有一个元素的情况
        if(nums.length==1)
            if(nums[0]==target) return 0;
            else return -1;

        // 首先判断数组是否有序（拿第一个元素和最后一个元素做比较）
        if(nums[start]>nums[end-1]) {
            // 无序数组，需将此数组一分为二

            // 从中间切开此数组
            int mid = start+(end-start)/2;

            int leftResult = BinarySearch(nums,target,0,mid);
            int rightResult = BinarySearch(nums,target,mid,end);

            if(leftResult!=-1) return leftResult;
            else if(rightResult!=-1) return rightResult;

            // 左右两边都找不到结果，返回-1
            return -1;
        }

        // 有序数组，二分扫描判断该有序数组是否存在值target
        int left = start;
        int right = end-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return -1;
    }
}
