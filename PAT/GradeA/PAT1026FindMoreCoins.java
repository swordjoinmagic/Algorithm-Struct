package PAT.GradeA;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 思路：
 *      1. 参考题解。
 *      DP。类似01背包，设f[i][j]为使用[i,N]个硬币，是否能够凑齐j块钱，
 *      状态转移方程如下：
 *          f[i][j] = f[i+1][j] || f[i+1][ j-A[i] ]
 *      求解顺序为：
 *      i : N->1
 *      j : 0->M
 *
 *      题目要求结果为字典序，则从使用的硬币从1->N，
 *      如果第i个硬币的f[i][M - A[i]]为True，那么可以使用该硬币，
 *      知道减为0，那么此时即为字典序。
 */
public class PAT1026FindMoreCoins {
    public static void main(String[] args){
        PAT1026FindMoreCoins pat1026 = new PAT1026FindMoreCoins();
        pat1026.Input();
    }

    // 硬币数量与要凑够的钱数
    int N,M;

    int[] A;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        A = new int[N+1];

        for(int i=1;i<=N;i++) A[i] = in.nextInt();

        Slove();
    }

    public void Slove(){

        Arrays.sort(A);

        // f[i][j]表示用[i,N]个硬币，凑够j块钱
        boolean[][] f = new boolean[N+2][M+1];

        // 初始化
        for(int i=1;i<=N+1;i++) f[i][0] = true;

        for(int i=N;i>=1;i--){
            for(int j=1;j<=M;j++){
                // 不使用或使用第i枚硬币是否可以凑够j块钱
                if(j-A[i]>=0)
                    f[i][j] = f[i+1][j] || f[i+1][ j-A[i] ];
            }
        }

        // 字典序顺序查找
        int total = M;
        for(int i=1;i<=N;i++){
            // 是否可以用这个硬币
            if(f[i+1][total-A[i]]){
                total-=A[i];
                System.out.print(A[i]+" ");
                if(total==0)
                    break;
            }
        }
        if(total==M){
            System.out.print("No Solution");
        }
    }

}
