package szptLesson.DP;

import java.util.Scanner;

public class MaxSum {
    int n;
    int[] array;

    public static void main(String[] args){
        MaxSum sum = new MaxSum();
        sum.Input();
        sum.Slove();
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        array = new int[n];
        for(int i=0;i<n;i++){
            array[i] = in.nextInt();
        }
    }

    public void Slove(){
        int[] minF = new int[n];
        int[] f = new int[n];
        f[0] = array[0];
        minF[0] = array[0];

        int max = f[0];
        for(int i=1;i<n;i++){
            minF[i] = Math.min(f[i-1]*array[i],array[i]);

            if(array[i]>=0)
                f[i] = Math.max(f[i-1]*array[i],array[i]);
            else
                f[i] = Math.max(minF[i-1]*array[i],f[i]);

            max = Math.max(max,f[i]);
        }

        System.out.println(max);
    }
}
