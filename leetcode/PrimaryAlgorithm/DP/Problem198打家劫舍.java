package leetcode.PrimaryAlgorithm.DP;

/**
 * 思路:
 *      1. DP,
 *      设f[i]为偷窃前i个房间能得到的最大例如
 *      f[i] = max(f[i-1],f[i-1]+A[i]);
 *      解释:
 *      偷袭前i个房间能得到最大利润为
 *      Max(偷窃前i-1个房间能得到的最大利润,
 *      偷窃前i-2个房间的最大利润加上当前房间i的利润)
 */
public class Problem198打家劫舍 {

    public static void main(String[] args){
        Problem198打家劫舍 problem198 = new Problem198打家劫舍();
        int[] array = new int[]{2,7,9,3,1};
        int result = problem198.rob(array);
        System.out.println(result);
    }

    public int rob(int[] nums) {
        if(nums.length<=0) return 0;
        if(nums.length<=1) return nums[0];
        int[] f = new int[nums.length+2];

        // 边界条件
        f[0] = nums[0];
        f[1] = Math.max(nums[0],nums[1]);

        for(int i=2;i<nums.length;i++){
            f[i] = Math.max(f[i-1],f[i-2]+nums[i]);
        }

        return f[nums.length-1];
    }
}
