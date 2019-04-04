package leetcode.StructPractice.array;

import java.util.Arrays;

/**
 * ○ 本题是第二次做
 *
 * × 在第二次做的时候,没有思路或思路不清晰
 *
 * 思路：
 *      1. 类似归并排序的思路.
 *      再开辟一个空间复杂度为O(m)的数组tempNums1,
 *      用于保存nums1,然后就可以用归并的方法了.
 *
 *      比较nums1[0]和nums2[0],tempNums1[0] = 大者,大者下标+1
 *      继续遍历以此类推
 *      时间复杂度O(N+M)
 */
public class Problem88合并两个有序数组 {

    public static void main(String[] args){
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{};
        new Problem88合并两个有序数组().merge(nums1,1,nums2,0);
        for(int data:nums1) System.out.print(" "+data);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 临时数组,用于保存num1数组
        int[] tempNums1 = Arrays.copyOfRange(nums1,0,m);

        int i = 0,j = 0;
        int index = 0;

        while (i<m && j<n){
            if(tempNums1[i]<nums2[j]){
                nums1[index++] = tempNums1[i++];
            }else {
                nums1[index++] = nums2[j++];
            }
        }

        while (i<m)
            nums1[index++] = tempNums1[i++];
        while (j<n)
            nums1[index++] = nums2[j++];
    }
}
