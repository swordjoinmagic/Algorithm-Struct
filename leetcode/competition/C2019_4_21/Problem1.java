package leetcode.competition.C2019_4_21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 思路：
 *      1. 生成一个 从 1..A[N]的数组，求A与该数组的补集
 *
 *      思路1错误，k太大了，k的单位为亿级别的，不能这样做。
 *
 *      2. 设数组第一个元素为start，那么在完美情况下
 *      start+K是他的的第K个欠缺数据。
 *
 *      但情况往往不完美，找到值小于等于start+K的数在数组中的下标（二分）
 *      则该下标就是这个缺失数据之前有多少未缺失数据，最后，
 *      start+K加上该下标index，
 *
 *      如果 start+K+index
 */
public class Problem1 {

    public static void main(String[] args){
        int[] nums = new int[]{1,3,2,4,5,746431,746432};
        int k = 10;

        int n = 746431;
        int index = Arrays.binarySearch(nums,n);

        int start = 0;

        // 当要找的数在数组中不存在时，A[trueIndex]表示正好小于等于该数的数在数组中的下标
        int trueIndex = Math.abs(index+1)-1;

        System.out.println(trueIndex);

        // 则true-start表示 该数 之前有多少个未缺失的数
        int noneLoseNumberCount = trueIndex-start;

        // 如果为0，那么表示A[start]+ Z 就是缺失元素
        if(noneLoseNumberCount == 0) {
            int result = nums[start]+k;
        }

        // 更新start
        start = trueIndex;



        System.out.println(trueIndex);

//        int result = new Problem1().missingElement(nums,k);
//        System.out.println(result);
    }
//    public int missingElement(int[] nums, int k) {
//
//    }

}
