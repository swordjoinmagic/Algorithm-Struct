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

        2. 设long[i]为深度为i的值最小的数的下标（这里的深度指的是最长上升子序列的长度）
           设Max为当前最大深度，设变量t -> 1~len 则有以下情况：

            1. 当A[t] > A[long[Max]]时，说明A[t]比当前深度最大的元素值更大，可以直接连接到A[long[Max]]处
            此时，令 Max = Max+1; long[Max] = t;

            2. 当A[t] <= A[long[Max]]时，说明A[t]比当前深度最大的元素值更小，不能直接连过去充当最长上升子序列，
            这时要从long[]数组中找到一个下标j，使得long[j]深度尽量大且A[t]>A[long[j]]。
            找到该下标（深度）j后，更新long[j+1]=t;(因为A[t]正好比A[long[j]]大，且A[t]正好比A[long[j+1]]小，
            说明此时的long[j+1]记录的不是同深度下值最小的数，要更新为t）

            遍历变量t，时间复杂度O(N)，从long[]数组中找到深度尽量大且A[t]>A[long[j]]的数，遍历所需复杂度O（N），
            二分查找所需复杂度O(LogN)，最终算法复杂度O(NLogN)
 *
 */
public class Problem300最长上升子序列 {

    public static void main(String[] args){
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int result = new Problem300最长上升子序列().nLogNLIS(nums);
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

    /**
     * 时间复杂度O(nlogn)的计算最长上升子序列方法
     * @return
     */
    public int nLogNLIS(int[] nums){
        int Max = 1;
        int[] Long = new int[nums.length+1];

        // 初始化long数组
        Long[0] = 0;

        // 这里计算的是以t下标为结尾的最长不降子序列的长度
        for(int t=1;t<nums.length;t++){
            if(nums[t]>nums[Long[Max]]){
                // 直接将nums[t]连上nums[long[Max]]
                Max += 1;
                Long[Max] = t;
            }else {
                // 二分查找下标j，使得j尽量大，且nums[long[j]]<nums[t]
                int j = binarySearch(Long,nums[t],nums,Max);
                // 更新Long数组
                Long[j+1] = t;
            }
        }

        return Max;
    }

    public int binarySearch(int[] Long,int key,int[] nums,int Max){
        int left = 0;
        int right = Max;

        while (left<right){
            int mid = left + (right-left)/2;

            // 找到一个下标j，使得nums[Long[j]] < key
            if(nums[Long[mid]] < key){

                // 使得j尽量大
                left = mid+1;

            }else{
                right = mid-1;
            }
        }

        return left;
    }

    public void print(int[] nums){
        for(int data : nums) System.out.print(data+" ");
        System.out.println();
    }
}
