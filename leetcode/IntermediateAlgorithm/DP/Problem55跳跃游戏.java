package leetcode.IntermediateAlgorithm.DP;

/**
 * 思路：
 *      1. 设F[i]表示从i位置（下标）出发，能否到达最后一个位置
 *      从最后一个元素触发，f[A.length] = true，状态转移方程如下:
 *
 *      F[i-1] = f[ 1~A[i-1]+i ] == true?(中间的表达式为or关系)
 *
 *      时间复杂度O(n2)
 *      2. 对思路1的改进。思路1的弊端在于要循环判断
 *      下标i前面A[i]个位置是不是为true，为true则可以到达
 *
 *      事实上，只需要判断i+A[i]>=n就可以判断当前下标是否可以到达末尾了
 *      依据是，既然可以跳的数量为A[i]，其最大跳跃数可以跳过n，
 *      那么他就一定能跳到n的位置，这里的n是当前数组的末尾。
 *
 *      为什么是当前数组？因为n需要更新，每次知道当前下标可以到末尾时，
 *      则 下一步 只需要跳到这个下标，就一定能跳到末尾。
 *
 *      所以此时更新n（数组末尾）为当前下标i
 */
public class Problem55跳跃游戏 {

    public static void main(String[] args){
        int[] nums = new int[]{2,5,0,0};
        Problem55跳跃游戏 problem55 = new Problem55跳跃游戏();
        boolean result = problem55.canJumpFast(nums);
        System.out.println(result);
    }

    public boolean canJump(int[] nums) {
        boolean[] f = new boolean[nums.length];

        // 初始化f
        f[nums.length-1] = true;

        for(int i=nums.length-1;i>=1;i--){
            for(int j=1;j<=nums[i-1];j++){
                f[i-1] = f[i-1] || f[j+i-1];
                if(f[i-1]) break;
            }

        }

        return f[0];
    }

    public boolean canJumpFast(int[] nums){
        boolean[] f = new boolean[nums.length];

        // 初始化f
        f[nums.length-1] = true;

        int n = nums.length-1;

        for(int i=nums.length-2;i>=0;i--){

            if(nums[i]+i>=n) {
                f[i] = true;

                // 更新n，截断后面元素，表示后面
                // 任意一个台阶只要跳到这个位置（最小位置）
                // 就一定能到末尾
                n = i;
            }

        }

        return f[0];
    }

    private void print(boolean[] f){
        for(boolean data : f){
            System.out.print(data+" ");
        }
        System.out.println();
    }
}
