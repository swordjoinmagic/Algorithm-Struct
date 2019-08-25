package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定两个序列,求这两个序列合为一个序列后的的中位数.
 *      PS: 给定的两个序列有序
 *
 * 思路:
 *      1. 归并排序.已有两个升序序列,归并一次的复杂度为O(N)
 *      N为较小序列的长度.
 */
public class PAT1029Median_25point {

    public static void main(String[] args){
        PAT1029Median_25point pat1029 = new PAT1029Median_25point();
        pat1029.Input();
    }

    long[] A;
    long[] B;

    int Na,Nb;

    // 归并后的结果序列
    long[] result;

    public void Input(){
        Scanner in = new Scanner(System.in);

        Na = in.nextInt();
        A = new long[Na];
        for(int i=0;i<Na;i++)
            A[i] = in.nextLong();

        Nb = in.nextInt();
        B = new long[Nb];
        for(int i=0;i<Nb;i++)
            B[i] = in.nextLong();

        Slove();
    }

    public void Slove(){

        int resultLen = Na+Nb;
        result = new long[resultLen];

        // 归并两个有序序列
        int i = 0;
        int a=0,b=0;
        for(;a<Na && b<Nb;){
            if(A[a]<B[b]){
                // 将A[a]放入result
                result[i++] = A[a];
                a++;
            }else {
                // 将B[b]放入result
                result[i++] = B[b];
                b++;
            }
        }
        if(a<Na){
            for(int j=a;j<Na;j++){
                result[i++] = A[j];
            }
        }
        if(b<Nb){
            for(int j=b;j<Nb;j++)
                result[i++] = B[j];
        }

        // 如果是奇数,那么中位数就是 len/2,
        // 如果是偶数,那么中位数就是 len/2-1
        int mid;
        if(resultLen%2==0)
            mid = resultLen/2-1;
        else
            mid = resultLen/2;
        System.out.println(result[mid]);
    }


}
