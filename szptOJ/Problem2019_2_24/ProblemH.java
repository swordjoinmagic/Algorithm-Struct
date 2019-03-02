package szptOJ.Problem2019_2_24;

import java.util.Scanner;

public class ProblemH {

    public static void main(String[] args){
        ProblemH h = new ProblemH();
        h.Input();
        h.Slove();
    }

    private int n;
    private int[] array;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        array = new int[n];
        for(int i=0;i<n;i++){
            array[i] = in.nextInt();
        }
    }

    public void Slove(){
        int[] zeroNums = new int[n];

        int oneNums = 0;

        int maxZeros = 0;

        for(int i=0;i<array.length;i++){
            if(array[i]==0){
                if(i-1>0)
                    zeroNums[i] = zeroNums[i-1]+1;
                else
                    zeroNums[i] = 1;

                if(zeroNums[i]>maxZeros)
                    maxZeros = zeroNums[i];
            }else {
                zeroNums[i] = 0;
                oneNums += 1;
            }
        }

        System.out.println(oneNums+maxZeros);
    }
}
