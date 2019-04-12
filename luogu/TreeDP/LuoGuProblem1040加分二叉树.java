package luogu.TreeDP;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class LuoGuProblem1040加分二叉树 {

    public static void main(String[] args){
        LuoGuProblem1040加分二叉树 problem1040 = new LuoGuProblem1040加分二叉树();
        problem1040.Input();
        problem1040.Slove();
    }

    // 节点数量
    int nodeCount;
    // 记录各节点分值
    int[] nodeScore;

    public void Input(){
        Scanner in = new Scanner(System.in);
        nodeCount = in.nextInt();

        nodeScore = new int[nodeCount+1];

        for(int i=1;i<=nodeCount;i++){
            nodeScore[i] = in.nextInt();
        }
    }

    public void Slove(){
        // 设f[i][j]为子序列a[i]到a[j]的最大加分,则动态转移方程如下所示:
        //                 根节点  左子树最大值      右子树最大值
        // f[i][j] = max( f[k][k]  + f[i][k-1]   *   f[k+1][j]   )
        // 其中 0 <= i <= k <= j < n

        int[][] f = new int[101][101];

        // 初始化
        for(int i=0;i<=nodeCount+1;i++){
            // 空子树记为1
            Arrays.fill(f[i],1);
        }
        for(int i=1;i<=nodeCount;i++)
            f[i][i] = nodeScore[i];


        for(int i=nodeCount;i>=1;i--){
            for(int j=i+1;j<=nodeCount;j++){
                for(int k=i;k<=j;k++){
                    f[i][j] = Math.max(f[k][k]+f[i][k-1]*f[k+1][j],f[i][j]);
                }
            }
        }

        System.out.println();
        for(int i=1;i<=nodeCount;i++){
            for(int j=1;j<=nodeCount;j++) {
                System.out.print(f[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(f[1][nodeCount]);
    }
}
