package szptOJ.Problem2019_2_24;

import java.util.Scanner;

public class ProblemB {
    public static void main(String[] args){
        ProblemB b = new ProblemB();
        b.Input();
        b.Slove();
    }

    private int n,k;

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
    }

    public void Slove(){

        int zeroNums = 0;
        int num = 0;

        for(int i=1;i<=10;i++){
            int result = n*i;
            int zero = getZero(result);
            if(zero>=k){
                System.out.print(result);
                return;
            }
            if(zero>zeroNums){
                num = result;
                zeroNums = zero;
            }
        }

        String result = String.valueOf(num);
        for(int i=0;i<k-zeroNums;i++){
            result += "0";
        }
        System.out.print(result);
    }

    public int getZero(int n){
        int result = 0;
        while (n%10==0 && n!=0){
            result += 1;
            n /= 10;
        }
        return result;
    }

    public int pow(int num,int x){
        int result = 1;
        for(int i=0;i<x;i++){
            result *= num;
        }
        return result;
    }
}
