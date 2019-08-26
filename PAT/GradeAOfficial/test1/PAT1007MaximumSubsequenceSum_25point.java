package PAT.GradeAOfficial.test1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题意:
 *      1. 求最大连续子序列
 *
 * 思路:
 *      1. 设f[i]为以i下标为结尾的最大连续子序列的值,
 *      则对于序列 { -2, 11, -4, 13, -5, -2 },
 *
 *      f[0] = A[0]
 *      f[1] = f[0]+A[1] or A[1] = 11
 *      f[2] = f[1]+A[2] or A[2] = 7
 *      f[3] = f[2]+A[3] or A[3] = 20
 *      f[4] = f[3]+A[4] or A[4] = 15
 *      ....
 */
public class PAT1007MaximumSubsequenceSum_25point {

    public static void main(String[] args){
        PAT1007MaximumSubsequenceSum_25point pat1007 = new PAT1007MaximumSubsequenceSum_25point();
        pat1007.Input();
    }

    int K;
    int[] A;

    int maxValue = Integer.MIN_VALUE;
    int right = 0,left = 0;

    // 每个最大连续子序列左端点的值
    int[] lefts;

    public void Input(){
        Scanner in = new Scanner(System.in);
        K = in.nextInt();
        A = new int[K];
        lefts = new int[K];

        int[] f = new int[K];
        Arrays.fill(f,Integer.MIN_VALUE);

        for(int i=0;i<K;i++){
            A[i] = in.nextInt();
            if(i==0){
                f[0] = A[0];
                maxValue = f[0];
                right = 0;
                lefts[i] = 0;
            }else {
                if(f[i-1]+A[i]>=A[i]) {
                    f[i] =  f[i - 1] + A[i];
                    lefts[i] = lefts[i-1];
                }else {
                    f[i] = A[i];
                    lefts[i] = i;
                }
                if(maxValue<f[i]){
                    maxValue = f[i];
                    right = i;
                }
            }
        }
        Slove();
    }

    public void Slove(){

        left = lefts[right];

        if(maxValue<0) { left=0;right=K-1;maxValue=0;}

        System.out.print(maxValue+" ");
        System.out.println(A[left]+" "+A[right]);
    }
}
