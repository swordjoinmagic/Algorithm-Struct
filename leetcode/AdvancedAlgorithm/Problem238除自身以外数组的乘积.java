package leetcode.AdvancedAlgorithm;

/**
 * 思路:
 *      1. 建立线段树,每次查询区间[l,r]的乘积,时间复杂度O(NLogN)
 *      2. 单调队列.类似广告印刷那题的思路.
 *      设数组L和R,其中
 *      L[i]表示 i 左边所有数字的乘积
 *      R[i]表示 i 右边所有数字的乘积
 *
 *      从左往右扫一遍,从右往左扫一遍,
 *      最后每个数字i的output[i] = L[i]*R[i]
 *
 *      时间复杂度O(N),空间复杂度O(2N)
 *
 *
 */
public class Problem238除自身以外数组的乘积 {

    public static void main(String[] args){
        Problem238除自身以外数组的乘积 problem238 = new Problem238除自身以外数组的乘积();
        int[] result = problem238.productExceptSelf(new int[]{1,2,3,4});
        for(int i:result) System.out.print(i+" ");
    }

    public int[] productExceptSelf(int[] nums) {
        int[] L = new int[nums.length];
        int[] R = new int[nums.length];

        L[0] = 1; L[1] = nums[0];
        // 从左往右,获得数字i左边所有数字的乘积
        for(int i=2;i<nums.length;i++){
            L[i] = nums[i-1] * L[i-1];
        }

        R[nums.length-1] = 1; R[nums.length-2] = nums[nums.length-1];
        // 从右往左,获得数字i右边所有数字的乘积
        for(int i=nums.length-3;i>=0;i--){
            R[i] = nums[i+1] * R[i+1];
        }

        int[] output = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            output[i] = L[i] * R[i];
        }
        return output;
    }
}
