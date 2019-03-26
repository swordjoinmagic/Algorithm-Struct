package leetcode.Other;

/**
 * 思路：
 *      1. 设f[i]为以下标i为截止的乘积最大的连续子序列
 *
 *      f[i] = max(f[i-1]*A[i],A[i])
 *
 *      设minF[i]为以下标i为截止的乘积最小的连续子序列
 *
 *      minF[i] = minF(minF[i-1]*A[i],A[i]);
 */
public class Problem152乘积最大子序列 {

    public static void main(String[] args){
        Problem152乘积最大子序列 problem152 = new Problem152乘积最大子序列();
        int reuslt =  problem152.maxProduct(new int[]{1,2,-1,-2,2,1,-2,1,4,-5,4});
        System.out.println(reuslt);
    }

    public int maxProduct(int[] array) {

        // f[i]保存以下标i为结尾的连续子序列最大乘积
        int[] f = new int[array.length];

        // minF[i]保存以下标i为结尾的连续子序列最小乘积
        int[] minF = new int[array.length];

        // 初始化f、minF
        f[0] = array[0];
        minF[0] = array[0];

        int max = array[0];

        for(int i=1;i<array.length;i++){

            if(array[i]>=0){
                f[i] = Math.max(f[i-1]*array[i],array[i]);
                minF[i] = Math.min(minF[i-1]*array[i],array[i]);
            }else{
                // 当前数<0时,表示乘上这个数,
                // 最大数会变成最小数,最小数会变成最大数
                // 下面进行翻转
                f[i] = Math.max(minF[i-1]*array[i],array[i]);
                minF[i] = Math.min(f[i-1]*array[i],array[i]);
            }

            max = Math.max(max,f[i]);
        }

        return max;
    }
}
