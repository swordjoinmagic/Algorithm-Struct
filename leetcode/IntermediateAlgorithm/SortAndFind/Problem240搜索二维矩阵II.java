package leetcode.IntermediateAlgorithm.SortAndFind;

/**
 *
 * 思路：
 *      1. 一行一行的用2分找，时间复杂度O(NLogN)
 */
public class Problem240搜索二维矩阵II {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        if(n==0) return false;
        for(int i=0;i<n;i++){
            if(BinarySearch(matrix[i],target)) return true;
        }
        return false;
    }

    private boolean BinarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]==target){
                return true;
            }else if(nums[mid]>target){
                // 向左区间找
                right = mid-1;
            }else if(nums[mid]<target){
                // 向右区间找
                left = mid+1;
            }
        }

        return false;
    }
}
