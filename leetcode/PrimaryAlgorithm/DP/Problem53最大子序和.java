package leetcode.PrimaryAlgorithm.DP;

/**
 * 思路:
 *      DP
 *      设f[i]为数组前i项且
 *      以i下标为结尾(这个连续子数组一定包含A[i])的
 *      连续子数组最大和
 *
 *      f[i] = max(f[i-1]+A[i],A[i])
 */
public class Problem53最大子序和 {

    public static void main(String[] args){
        Problem53最大子序和 problem53 = new Problem53最大子序和();
        int[] array = new int[]{6,-1,5,4,-7};
        int reuslt = problem53.maxSubArray(array);
        System.out.println(reuslt);
    }

    private int max;
    public int maxSubArray(int[] nums) {
        if(nums.length<=0) return 0;

        int[] f = new int[nums.length];

        // 边界条件
        f[0] = nums[0];
        max = f[0];

        for(int i=1;i<nums.length;i++){
            f[i] = Math.max(f[i-1]+nums[i],nums[i]);
            if(f[i]>max)    max = f[i];
        }

        return max;

    }
}
