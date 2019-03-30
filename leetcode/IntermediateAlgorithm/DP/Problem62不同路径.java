package leetcode.IntermediateAlgorithm.DP;

/**
 * 思路：
 *      1. 设f[i][j]为从起点到目标位置(i,j)一共有多少不同路径
 *      状态转移方程如下：
 *      f[i][j] = f[i-1][j](上) + f[i][j-1](左);
 *      时间复杂度O(n2)
 */
public class Problem62不同路径 {

    public static void main(String[] args){
        Problem62不同路径 problem62 = new Problem62不同路径();
        int result = problem62.uniquePaths(7,3);
        System.out.println(result);
    }

    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        // 初始化
        f[0][0] = 1;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i-1>=0)
                    f[i][j] += f[i-1][j];
                if(j-1>=0)
                    f[i][j] += f[i][j-1];
            }
        }

        return f[m-1][n-1];
    }
}
